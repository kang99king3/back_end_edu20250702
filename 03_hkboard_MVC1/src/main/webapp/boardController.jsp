<%@page import="com.hk.board.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.board.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//1단계:command값 받기 -> 어떤 요청인지 확인 값 받기
	//    -요청값: 별도의 command라는 값을 전달
	//    -요청url: boardlist.board  :MVC2방식일때
	String command=request.getParameter("command");
	
	//2단계: DAO객체 생성
	HkDao dao=new HkDao();
	
	//3단계:요청 분기
	if(command.equals("boardlist")){  // 글목록 요청 처리
		//4단계:파라미터 받기 (글목록에서는 받을 파라미터가 X)
		
		//5단계:dao 메서드 실행
		List<HkDto> list=dao.getAllList();//DB로부터 글목록 데이터 가져오기
		
		//6단계:Scope 객체에 담기
		//request스코프: 객체 전달범위 
		// 요청페이지 ----> 응답페이지
		request.setAttribute("list", list);//["list":list]
		
		//7단계: 페이지 응답(이동)
		pageContext.forward("boardlist.jsp");
	}else if(command.equals("insertboardform")){//글쓰기폼요청
		response.sendRedirect("insertboardform.jsp");
	}
%>
</body>
</html>





