package com.hk.stock.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PriceTick {
	 private String symbol;      // ex: 005930
	    private double price;       // 체결가
	    private long timestamp;     // 수신 시간 (ms)
    
    
}
