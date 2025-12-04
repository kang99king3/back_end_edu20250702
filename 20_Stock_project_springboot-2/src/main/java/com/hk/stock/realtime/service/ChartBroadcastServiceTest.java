package com.hk.stock.realtime.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hk.stock.dto.PriceTickFull;

import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
public class ChartBroadcastServiceTest {

//    private final StringRedisTemplate redis;
//    private final SimpMessagingTemplate messaging;
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    // 브로드캐스트할 종목 목록 (원하면 DB로 변경 가능)
//    private final List<String> watchList = List.of("000660", "005930");
//
//    /**
//     * 2초마다 Redis에서 최신 가격을 가져와 WebSocket으로 방송
//     */
//    @Scheduled(fixedRate = 2000)
//    public void broadcastChart() {
//
//        for (String symbol : watchList) {
//
//            String keySeries = "tick:series:" + symbol;
//            List<String> seriesJson = redis.opsForList().range(keySeries, 0, -1);
//
//            if (seriesJson == null || seriesJson.isEmpty()) {
//                continue;
//            }
//
//            List<PriceTickFull> ticks = new ArrayList<>();
//            for (String js : seriesJson) {
//                try {
//                    ticks.add(mapper.readValue(js, PriceTickFull.class));
//                } catch (Exception ignore) {}
//            }
//
//            // 값이 없으면 skip
//            if (ticks.isEmpty()) continue;
//
//            // ------- 네이버 스타일 계산 -------
//            double open = ticks.get(0).getPrice();
//            double current = ticks.get(ticks.size()-1).getPrice();
//
//            double high = ticks.stream().mapToDouble(PriceTickFull::getPrice).max().orElse(current);
//            double low  = ticks.stream().mapToDouble(PriceTickFull::getPrice).min().orElse(current);
//
//            long volume = ticks.stream().mapToLong(PriceTickFull::getVolume).sum();
//
//            // ------- WebSocket 메시지 생성 -------
//            ChartResponse response = new ChartResponse(
//                symbol,
//                ticks,
//                open,
//                high,
//                low,
//                current,
//                volume
//            );
//
//            // 브라우저는 /topic/chart.종목코드 를 구독함
//            messaging.convertAndSend("/topic/chart." + symbol, response);
//        }
//    }
//
//    // ---- WebSocket으로 보낼 DTO ----
//    record ChartResponse(
//            String symbol,
//            List<PriceTickFull> ticks,
//            double open,
//            double high,
//            double low,
//            double current,
//            long volume
//    ) {}
}