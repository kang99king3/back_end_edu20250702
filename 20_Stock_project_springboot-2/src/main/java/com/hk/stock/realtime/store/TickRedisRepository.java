package com.hk.stock.realtime.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.stock.dto.PriceTickFull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TickRedisRepository {

    private final StringRedisTemplate redis;
    private final ObjectMapper mapper = new ObjectMapper();

    public void saveTick(PriceTickFull tick) {
        try {
            String keyLatest = "tick:latest:" + tick.getSymbol();
            String keySeries = "tick:series:" + tick.getSymbol();
//            System.out.printf("latest-%s, keySeries-%s \n",keyLatest,keySeries);
            String json = mapper.writeValueAsString(tick);

            // 최신 틱 저장
            redis.opsForValue().set(keyLatest, json);

            // 최근 차트용 리스트 push
            redis.opsForList().rightPush(keySeries, json);
            redis.opsForList().trim(keySeries, -600, -1); // 최근 600개만 유지

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** 최신 1건 조회 */
    public PriceTickFull getLatestTick(String symbol) {
        try {
//        	System.out.println("tick:latest:" + symbol);
            String json = redis.opsForValue().get("tick:latest:" + symbol);
//            System.out.println(json);
            if (json == null) return null;
            return mapper.readValue(json, PriceTickFull.class);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

    /** 최근 리스트 조회 (600개) */
    public List<PriceTickFull> getSeries(String symbol) {
        try {
            List<String> list = redis.opsForList()
                .range("tick:series:" + symbol, 0, -1);

            List<PriceTickFull> result = new ArrayList<>();
            if (list == null) return result;

            for (String json : list) {
                result.add(mapper.readValue(json, PriceTickFull.class));
            }
            return result;

        } catch (Exception e) {
            return List.of();
        }
    }
}
