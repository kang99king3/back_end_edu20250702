package com.hk.stock.realtime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

	@GetMapping("/realtime")
	public String realTimeView() {
		return "realtime";
	}
	
	@GetMapping("/candle")
	public String candleView() {
		return "candle";
	}
}
