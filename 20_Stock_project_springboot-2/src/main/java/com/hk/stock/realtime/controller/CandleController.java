package com.hk.stock.realtime.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hk.stock.realtime.service.CandleService;
import com.hk.stock.realtime.service.CandleService.CandleDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chart")
public class CandleController {

    private final CandleService candleService;

    @GetMapping("/candles/{symbol}")
    public List<CandleDto> getCandles(@PathVariable String symbol) {
        return candleService.getOneMinuteCandles(symbol);
    }
}