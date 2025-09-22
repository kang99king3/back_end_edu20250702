package com.hk.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/HelloServletOld.do")
public class HelloServletOld extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HelloServletOld() {
    	
    }

    //init(): 서블릿 객체가 생성될때 최초 한번 실행되는 메서드
    @Override
    public void init() throws ServletException {
    	System.out.println("init():최초 한번 실행");
    }
    
    //ServletConfig객체의 사용
    // --> init()메서드에서 파라미터를 통해 얻을 수 있다.
    @Override
    public void init(ServletConfig config) throws ServletException {
    	//web.xml에 정의된 init 값을 가져오기
    	String name=config.getInitParameter("name");
    	System.out.println("서블릿초기값:"+name);
    	
    	//ServletContext객체 얻어오기(JSP에서 scope객체중에 application객체를 의미함)
    	ServletContext application=config.getServletContext();
    	application.setAttribute("id", "hk");//객체저장
    	String id=(String)application.getAttribute("id");
    	System.out.println("application값가져오기:"+id);
    	
    	//-- web.xml에 servletContext 값을 정의하고, 가져올 수 도 있다.
    	String driver=application.getInitParameter("driver");
    	System.out.println("application값가져오기:"+driver);
    	
    }
    
    //service(): 요청과 응답에 대한 처리 -> doGet()/doPost()구현함
    //client에서 get방식으로 localhost:8080/06_hello_servlet/HelloServletOld.do?seq=10&id=hk
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청주소:"+request.getRequestURI());
		System.out.println("요청방식:get방식");
		
		//인코딩처리: Filter로 처리하면 별도의 서블릿에 작성할 필요 없음
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
		
		//파리미터 받기
		String param=request.getParameter("param");
		
		//서블릿에서 HTML로 응답하기
		PrintWriter out=response.getWriter();
		out.print("<h1>서블릿개념</h1>");
		out.print("<h2>서블릿 기본 내용 알아보기</h2>");
		out.print("<h2>서블릿에서 받은 파라미터"+param+"</h2>");
		out.print("<h3><a href='index.jsp'>index</a></h3>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청방식:post방식");
		doGet(request, response);
	}

	
	@Override
	public void destroy() {
		System.out.println("요청이 더이상 없으면 자동으로 서블릿객체를 소멸시킨다.");
	}
}






