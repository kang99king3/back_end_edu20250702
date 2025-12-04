package com.hk.stock.realtime.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.stock.dto.PriceTickFull;
import com.hk.stock.realtime.controller.FrontController;
import com.hk.stock.realtime.store.TickRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//캔들차트에 사용할 데이터만 redis에 따로 저장함
@Service
@RequiredArgsConstructor
@Slf4j
public class CandleSchedulerService {

    private final TickRedisRepository redisRepo;
    private final StringRedisTemplate redisTemplate; // Redis에 직접 저장하기 위해 필요
    private final ObjectMapper mapper = new ObjectMapper();
    
    // 이 예시에서는 삼성전자(005930)와 SK하이닉스(000660)만 처리한다고 가정합니다.
    private static final String[] TARGET_SYMBOLS = {"005930", "000660", "450080"};

    /**
     * 매 분 0초에 실행되어 직전 분의 캔들을 확정하고 히스토리 DB에 저장합니다.
     * (예: 10:01:00 에 실행되어 10:00:00 ~ 10:00:59 데이터를 처리)
     */
    @Scheduled(cron = "0 * * * * *") // 매 분 0초에 실행
    public void aggregateAndSaveCandle() {
        System.out.println("aggregate스케쥴러실행");
    	LocalDateTime nowKst = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        
        // 한국 장 마감 후 (15:30:00 이후) 또는 장 시작 전 (09:00:00 이전)에는 실행하지 않습니다.
        if (nowKst.getHour() < 9 || (nowKst.getHour() > 15 || (nowKst.getHour() == 15 && nowKst.getMinute() >= 30))) {
            log.info("Market is closed or not open yet. Skipping candle aggregation.");
            return;
        }

        for (String symbol : TARGET_SYMBOLS) {
            try {
                // 1. Redis에서 단기 틱 버퍼 (keySeries) 데이터 가져오기
                List<PriceTickFull> ticks = redisRepo.getSeries(symbol); 
                
                // 2. 직전 분의 캔들 데이터만 추출 및 계산
                CandleService.CandleDto candle = createPreviousMinuteCandle(ticks, nowKst);
                System.out.println("candle:"+candle);
                if (candle != null) {
                    // 3. 확정된 캔들을 Redis 히스토리 리스트에 저장
                    saveCandleHistory(symbol, candle);
                }
            } catch (Exception e) {
                log.error("Failed to aggregate and save candle for symbol: {}", symbol, e);
            }
        }
    }

    /** 직전 분의 캔들 (OHLC)을 계산하는 메서드 */
    private CandleService.CandleDto createPreviousMinuteCandle(List<PriceTickFull> ticks, LocalDateTime currentTime) {
        
        if (ticks == null || ticks.isEmpty()) return null;

        // 직전 분의 시간대 계산 (예: 10:01 실행 시, 10:00 분의 데이터 필요)
        LocalDateTime previousMinute = currentTime.minusMinutes(1).withSecond(0).withNano(0);
        long previousMinuteKey = previousMinute.getHour() * 60 + previousMinute.getMinute();
        long candleStartTs = previousMinute.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();

        Double open = null;
        Double high = null;
        Double low = null;
        Double close = null;
        
        for (PriceTickFull tick : ticks) {
            LocalDateTime tickTime = Instant.ofEpochMilli(tick.getTs())
                                          .atZone(ZoneId.of("Asia/Seoul"))
                                          .toLocalDateTime();
            long tickMinuteKey = tickTime.getHour() * 60 + tickTime.getMinute();
            
            // 직전 분의 데이터만 사용 (현재 MinuteKey와 일치하는 것)
            if (tickMinuteKey == previousMinuteKey) {
                double price = tick.getPrice();
                
                if (open == null) {
                    open = price; // 첫 번째 틱이 시가
                    high = price;
                    low = price;
                }
                
                high = Math.max(high, price);
                low = Math.min(low, price);
                close = price; // 마지막 틱이 종가
            }
        }
        
        // 틱 데이터가 존재했을 경우에만 캔들 생성
        if (open != null) {
            CandleService.CandleDto candle = new CandleService.CandleDto(previousMinuteKey, candleStartTs);
            candle.open = open;
            candle.high = high;
            candle.low = low;
            candle.close = close;
            return candle;
        }
        return null;
    }
    
    /** 확정된 캔들을 Redis 히스토리 저장소에 누적 저장 */
    private void saveCandleHistory(String symbol, CandleService.CandleDto candle) throws JsonProcessingException {
        // Redis Sorted Set 사용: Time(Score)와 Candle Data(Member)를 묶어 저장
        // Key: candle:history:005930
        // Member: Candle JSON String
        // Score: candle.ts (Time)
        
        String keyHistory = "candle:history:" + symbol;
        String json = mapper.writeValueAsString(candle);
        
        // Sorted Set에 추가 (ts를 score로 사용하여 시간 순서대로 정렬)
        redisTemplate.opsForZSet().add(keyHistory, json, candle.ts);
        
        // 참고: Sorted Set은 중복된 score가 들어와도 member가 다르면 저장되지만, 
        // 1분봉은 ts가 고유하므로 사실상 덮어쓰기 효과를 가집니다. (안전함)
        
        log.info("Saved 1-min candle for {}: {}", symbol, new java.util.Date(candle.ts));
    }
}