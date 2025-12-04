package com.hk.stock.realtime.service;
/*
import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.stock.dto.PriceTickFull;
import com.hk.stock.realtime.store.TickRedisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandleService {

    private final TickRedisRepository redisRepo;
    private final ObjectMapper mapper = new ObjectMapper();

    // Redis에서 최근 tick 리스트 가져와서 1분봉 캔들 생성 
    public List<CandleDto> getOneMinuteCandles(String symbol) {

        List<PriceTickFull> list = redisRepo.getSeries(symbol);
        List<CandleDto> candles = new ArrayList<>();

        CandleDto current = null;

        for (PriceTickFull tick : list) {
            try {
//                PriceTickFull tick = mapper.readValue(json, PriceTickFull.class);

                long ts = tick.getTs();
                LocalDateTime time = Instant.ofEpochMilli(ts)
                        .atZone(ZoneId.of("Asia/Seoul"))
                        .toLocalDateTime();

                int minuteKey = time.getHour() * 60 + time.getMinute();

                if (current == null || current.minuteKey != minuteKey) {

                    if (current != null) candles.add(current);

                    current = new CandleDto(minuteKey, ts);
                    current.open = tick.getPrice();
                    current.high = tick.getPrice();
                    current.low = tick.getPrice();
                    current.close = tick.getPrice();

                } else {
                    current.high = Math.max(current.high, tick.getPrice());
                    current.low = Math.min(current.low, tick.getPrice());
                    current.close = tick.getPrice();
                }

            } catch (Exception ignore) {}
        }

        if (current != null) candles.add(current);

        return candles;
    }

    @lombok.Data
    public static class CandleDto {
        public long minuteKey;  // 분 단위 그룹
        public long ts;
        public double open;
        public double high;
        public double low;
        public double close;

        public CandleDto(long minuteKey, long ts) {
            this.minuteKey = minuteKey;
            this.ts = ts;
        }
    }
}
*/
import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate; // 👈 추가: Sorted Set 접근용
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.stock.dto.PriceTickFull;
import com.hk.stock.realtime.store.TickRedisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandleService {

    private final TickRedisRepository redisRepo;
    private final StringRedisTemplate redisTemplate; // 👈 추가: Redis Sorted Set 접근용
    private final ObjectMapper mapper = new ObjectMapper();

    /** * Redis Sorted Set에서 히스토리 캔들을 로드하고, 
     * List에서 현재 진행 중인 캔들을 계산하여 조합해 반환 
     */
    public List<CandleDto> getOneMinuteCandles(String symbol) {
        
        // 1. [핵심] 09시 KST 타임스탬프 계산 (오늘 데이터의 시작점)
        LocalDateTime todayStart = LocalDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate().atTime(9, 0, 0);
        long marketStartTs = todayStart.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        
        String keyHistory = "candle:history:" + symbol;
        List<CandleDto> candles = new ArrayList<>();

        // 2. [히스토리 로드] Sorted Set에서 09시 이후에 저장된 완성 캔들을 시간 순으로 가져옴
        // (Sorted Set은 Score=ts를 기준으로 정렬되어 있으므로 별도 정렬 불필요)
        Set<String> historicalCandleJsons = redisTemplate.opsForZSet()
                                                    .rangeByScore(keyHistory, marketStartTs, Double.MAX_VALUE);
        System.out.println("historicalCandleJsons:"+historicalCandleJsons);
        if (historicalCandleJsons != null) {
            for (String json : historicalCandleJsons) {
                try {
                    // 히스토리 캔들 추가
                    candles.add(mapper.readValue(json, CandleDto.class));
                    System.out.println(mapper.readValue(json, CandleDto.class));
                } catch (Exception ignore) {
                	ignore.printStackTrace();
                }
            }
        }
        
        // 3. [실시간 캔들 계산] 현재 진행 중인 분의 캔들을 계산
        List<PriceTickFull> currentTicks = redisRepo.getSeries(symbol);
        CandleDto currentCandle = createCurrentMinuteCandle(currentTicks);
        
        if (currentCandle != null) {
            // 현재 분의 캔들을 히스토리 리스트에 추가하거나, 마지막 캔들을 갱신
            
            if (!candles.isEmpty() && currentCandle.ts == candles.get(candles.size() - 1).ts) {
                // 직전 스케줄러가 저장한 캔들과 현재 계산한 캔들의 타임스탬프가 같으면 (현재 분 진행 중)
                
                // 마지막 캔들의 고가, 저가, 종가만 갱신 (시가는 이미 확정되었으므로 건드리지 않음)
                CandleDto last = candles.get(candles.size() - 1);
                last.high = currentCandle.high;
                last.low = currentCandle.low;
                last.close = currentCandle.close;
            } else {
                // 새로운 분이 시작되었거나 히스토리 데이터가 비어있으면 현재 캔들 추가
                candles.add(currentCandle);
            }
        }

        return candles;
    }

    /** * Redis List (short buffer)에서 현재 분의 캔들을 계산
     * 이 메서드는 이전 CandleService의 틱 계산 로직을 기반으로 합니다.
     */
    private CandleDto createCurrentMinuteCandle(List<PriceTickFull> ticks) {
        if (ticks == null || ticks.isEmpty()) return null;

        // 현재 분의 시간 계산
        LocalDateTime nowKst = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        long currentMinuteKey = nowKst.getHour() * 60 + nowKst.getMinute();
        
        CandleDto current = null;
        
        for (PriceTickFull tick : ticks) {
            try {
                long ts = tick.getTs();
                LocalDateTime time = Instant.ofEpochMilli(ts)
                        .atZone(ZoneId.of("Asia/Seoul"))
                        .toLocalDateTime();

                int minuteKey = time.getHour() * 60 + time.getMinute();

                // 현재 분의 틱만 처리 (이전 분의 틱은 스케줄러가 처리했으므로 무시)
                if (minuteKey == currentMinuteKey) {
                    
                    // 캔들 생성/갱신 로직 (이전 코드와 동일)
                    if (current == null) {
                        
                        // 현재 분의 정각 시간으로 보정
                        LocalDateTime candleStartTime = time.withSecond(0).withNano(0);
                        long candleStartTs = candleStartTime
                                                .atZone(ZoneId.of("Asia/Seoul"))
                                                .toInstant()
                                                .toEpochMilli();
                        
                        current = new CandleDto(minuteKey, candleStartTs);
                        current.open = tick.getPrice();
                        current.high = tick.getPrice();
                        current.low = tick.getPrice();
                        current.close = tick.getPrice();
                    } else {
                        current.high = Math.max(current.high, tick.getPrice());
                        current.low = Math.min(current.low, tick.getPrice());
                        current.close = tick.getPrice();
                    }
                }
            } catch (Exception ignore) {}
        }
        return current;
    }


    @lombok.Data
    public static class CandleDto {
        public long minuteKey;
        public long ts;
        public double open;
        public double high;
        public double low;
        public double close;

        public CandleDto() {
        	
        }
        
        public CandleDto(long minuteKey, long ts) {
            this.minuteKey = minuteKey;
            this.ts = ts;
        }
    }
}