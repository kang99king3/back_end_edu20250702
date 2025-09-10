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
<%  // 회원등록기능을 참고하여 구현해보세요

	//UserDao클래스에 구현할 내용
	//     updateUser()메서드를 구현
	//       --> update문을 실행
	
	//userUpdate.jsp에서 구현할 내용
	// 파라미터 받기
	String userId=request.getParameter("userId");
	String name=request.getParameter("name");
	String birthYear=request.getParameter("birthYear");
	int birthYearInt=Integer.parseInt(birthYear); //String->int
	String addr=request.getParameter("addr");
	String mobile1=request.getParameter("mobile1");
	String mobile2=request.getParameter("mobile2");
	String height=request.getParameter("height");
	int heightInt=Integer.parseInt(height); //String->int
	// dao객체 생성
	// updateUser() 실행
	// 응답 페이지로 이동: 수정성공하면 index.jsp로 이동, 실패하면 error.jsp이동



%>
</body>
</html>








