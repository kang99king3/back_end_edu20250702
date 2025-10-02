package com.hk.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.board.dtos.HkDto;
import com.hk.board.service.IHkService;

@Controller
//@RequestMapping("/board")  --> /board/boardlist.do
public class HomeController {

	@Autowired
	private IHkService hkService;
	
	@RequestMapping(value = "/home.do",method = RequestMethod.GET)
	public String home() {
		
		return "home";// WEB-INF/views/home.jsp로 응답 : ViewResolver가 찾아줌
	}
	
	//URL맵핑작업
	@RequestMapping(value="/boardlist.do",
			        method = RequestMethod.GET)
	public String boardList(HttpServletRequest request,
							Model model) {
		System.out.println("글목록 요청");
		List<HkDto> list=hkService.getAllList();
//		request.setAttribute("list", list);//가능함
		model.addAttribute("list", list);//스프링문법
		
		//viewResolver객체가 찾아 준다.
		return "boardlist";//forward기능으로 실행됨
//		return "redirect:boardlist.do";//redirect방식으로 응답할 경우 문법
	}
	
}





