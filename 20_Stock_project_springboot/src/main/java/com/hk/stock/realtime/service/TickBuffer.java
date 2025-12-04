package com.hk.stock.realtime.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;

import com.hk.stock.dto.PriceTick;

@Component
public class TickBuffer {

	/** 모든 틱을 순차적으로 담는 큐 (기존 기능 유지) */
    private final Queue<PriceTick> queue = new ConcurrentLinkedQueue<>();

    /** 🟢 각 종목(symbol)의 최신 PriceTick 1개만 저장 */
    private final Map<String, PriceTick> lastTickMap = new ConcurrentHashMap<>();


    /** 새 데이터 추가 */
    public void add(PriceTick data) {
        queue.add(data);

        // 최신 데이터 저장
        if (data.getSymbol() != null) {
            lastTickMap.put(data.getSymbol(), data);
        }
    }


    /** UDP 브로드캐스트용: 큐에서 모두 빼오기 */
    public Collection<PriceTick> drain() {
        List<PriceTick> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }
        return list;
    }


    /** 🟢 특정 종목의 최신 tick 1개 조회 (네이버처럼 첫 화면에 사용) */
    public PriceTick getLastTick(String symbol) {
        return lastTickMap.get(symbol);
    }
}
