<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세조회</title>
</head>
<body>
<div id="container">
	<div class="main">
		<div>
			<span>
			<%=ldto.getId()%>[<%=ldto.getRole()%>]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userController.jsp?command=userinfo&id=<%=ldto.getId()%>">나의 정보</a>
			</span>
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
	</div>
	<div class="content">
	<%
// 		UserDto dto=(UserDto)request.getAttribute("dto");
	%>
		<h1>사용자 페이지</h1>
		<div id="myinfo">
			<form action="userController.jsp" method="post">
				<input type="hidden" name="command" value="userupdate"/>
				<input type="hidden" name="id" value="${dto.id}"/>
			</form>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>








