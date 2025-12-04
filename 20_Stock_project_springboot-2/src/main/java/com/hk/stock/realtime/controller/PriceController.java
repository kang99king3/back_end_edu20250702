package com.hk.stock.realtime.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hk.stock.dto.PriceTickFull;
import com.hk.stock.realtime.store.TickRedisRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/price")
@RequiredArgsConstructor
public class PriceController {

    private final TickRedisRepository redisRepo;
    
    /** 차트 초기 데이터 */
    @GetMapping("/history/{symbol}")
    public List<PriceTickFull> getHistory(@PathVariable String symbol) {
        return redisRepo.getSeries(symbol);
    }

    /** 현재가 1건 */
    @GetMapping("/latest/{symbol}")
    public PriceTickFull getLatest(@PathVariable String symbol) {
        return redisRepo.getLatestTick(symbol);
    }
}