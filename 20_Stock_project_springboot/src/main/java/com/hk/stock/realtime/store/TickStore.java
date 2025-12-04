package com.hk.stock.realtime.store;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.hk.stock.dto.PriceTick;

@Component
public class TickStore {

    private final Map<String, PriceTick> latest = new ConcurrentHashMap<>();

    public void update(PriceTick tick) {
        latest.put(tick.getSymbol(), tick);
    }

    public PriceTick get(String symbol) {
        return latest.get(symbol);
    }

    public Map<String, PriceTick> getAll() {
        return latest;
    }
}
