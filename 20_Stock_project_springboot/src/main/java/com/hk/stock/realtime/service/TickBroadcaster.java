package com.hk.stock.realtime.service;

import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hk.stock.dto.PriceTick;
import com.hk.stock.realtime.store.TickStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TickBroadcaster {

    private final TickStore store;
    private final SimpMessagingTemplate messaging;

    @Scheduled(fixedRate = 2000) // 네이버 스타일 : 2초 업데이트
    public void broadcast() {
        for (Map.Entry<String, PriceTick> e : store.getAll().entrySet()) {
            PriceTick tick = e.getValue();
            messaging.convertAndSend("/topic/price." + tick.getSymbol(), tick);
        }
    }
}
