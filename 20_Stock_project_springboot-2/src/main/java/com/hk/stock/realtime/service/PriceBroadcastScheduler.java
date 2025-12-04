package com.hk.stock.realtime.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hk.stock.dto.PriceTickFull;
import com.hk.stock.realtime.store.TickRedisRepository;

import lombok.RequiredArgsConstructor;

//스케쥴러가 최근데이터를 특정 종목(symbol)만 클라이언트로 보냄(realTime페이지)
@Component
@RequiredArgsConstructor
public class PriceBroadcastScheduler {

    private final TickRedisRepository redisRepo;
    private final SimpMessagingTemplate messaging;

    private final Map<String, Long> lastTimestamp = new ConcurrentHashMap<>();

    /** 1초마다 실행 */
    @Scheduled(fixedRate = 1000)
    public void broadcastLatest() {
//    	System.out.println("[Scheduler] broadcastLatest tick");
        List<String> symbols = List.of("005930", "000660", "450080");

        for (String s : symbols) {

            PriceTickFull tick = redisRepo.getLatestTick(s);
//            System.out.println("[Scheduler] symbol=" + s + ", tick=" + tick);
            
            if (tick == null) continue;

            long ts = tick.getTs(); // timestamp
//            System.out.println(lastTimestamp.containsKey(s));
            if (!lastTimestamp.containsKey(s) || lastTimestamp.get(s) != ts) {

                lastTimestamp.put(s, ts);

                messaging.convertAndSend("/topic/price." + s, tick);
//                System.out.println("Sent -> " + s + " : " + tick.getPrice());
            }
        }
    }
}

