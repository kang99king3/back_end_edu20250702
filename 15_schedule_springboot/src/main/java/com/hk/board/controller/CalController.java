package com.hk.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.board.service.CalServiceImp;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/schedule")
public class CalController {
	//  boardlist.do , boarddetail.do...
	// board/boardlist.do , board/boarddetail.do
	// board/* --> 로그인확인
	
	@Autowired
	private CalServiceImp calService;

	// localhost:9090/schedule/
	@GetMapping("/")
	public String home() {
		System.out.println("index");
		return "index";
	}
	
	// localhost:9090/member/loginpage   --> /member/*
	// localhost:9090/schedule/calendar  --> /schedule/* --> login 확인 기능 실행
	@GetMapping("/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		System.out.println("calendar페이지");
		//request(요청)객체를 전달하면, 받은쪽에서도 요청처리를 할 수 있다.
		Map<String, Integer> calMap = calService.makeCalendar(request);
		model.addAttribute("calMap", calMap);
		
		return "calboard/calendar";
	}
}







