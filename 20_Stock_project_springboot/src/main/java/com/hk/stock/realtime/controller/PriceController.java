package com.hk.stock.realtime.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hk.stock.dto.PriceTick;
import com.hk.stock.realtime.store.TickStore;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
public class PriceController {

    private final TickStore store;

    @GetMapping("/{symbol}")
    public PriceTick get(@PathVariable String symbol) {
        return store.get(symbol);
    }
}

