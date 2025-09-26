package com.hk.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hk.board.dao.AnsDao;
import com.hk.board.dto.AnsDto;

//servlet으로 만들기: HttpServlet을 상속받아야 함
@WebServlet("*.board")
public class AnsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet()에서만 코드 작성하면 됨
		
		//command 값 구하기
		//예) 글목록 요청: http://localhost:8080/09_ans~~/boardlist.board
		//    글추가 요청:  ~~~~~~                       /insertboard.board
		String command=request.getRequestURI()  //09_ans~~/boardlist.board
							  .substring(request.getContextPath().length());
		
		System.out.println("요청Command:"+command);
		
		AnsDao dao=new AnsDao();
		
		if(command.equals("/boardlist.board")) {
			List<AnsDto> list=dao.getAllList();
			request.setAttribute("list", list);
			dispatch("boardlist.jsp", request, response);
		}else if(command.equals("/insertboardform.board")) {
			response.sendRedirect("insertboardform.jsp");
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	//forward 기능 구현
	public void dispatch(String url, HttpServletRequest request,
									 HttpServletResponse response) 
									throws ServletException, IOException {
		request.getRequestDispatcher(url)
		       .forward(request, response);
	}
}




