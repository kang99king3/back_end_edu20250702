<%@page import="com.hk.board.daos.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정폼</title>
</head>
<%
	String userId=request.getParameter("userId");
	UserDao dao=new UserDao();
	dao.getUser(userId);
	//     쿼리 실행: select * from usertbl where userId=userId;
%>
<body>

</body>
</html>


