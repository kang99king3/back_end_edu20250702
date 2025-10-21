package com.hk.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class CalController {
	//  boardlist.do , boarddetail.do...
	// board/boardlist.do , board/boarddetail.do
	// board/* --> 로그인확인
	
	// localhost:9090/schedule/
	@GetMapping("/")
	public String home() {
		System.out.println("index");
		return "index";
	}
	
	@GetMapping("/calendar")
	public String calendar() {
		System.out.println("calendar페이지");
		return "calboard/calendar";
	}
}







