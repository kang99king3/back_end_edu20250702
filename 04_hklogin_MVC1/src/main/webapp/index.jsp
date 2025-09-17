<%@ include file="header.jsp" %>
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
<% //로그인 실패시 전달되는 메시지를 받는다.
   String msg=request.getParameter("msg"); %>
<form action="userController.jsp" method="post">
	<input type="hidden" name="command" value="login">
	<h1>Login</h1>
	<input type="text" name="id" placeholder="ID" 
								 required="required"/>
	<input type="password" name="password" 
								 placeholder="password" 
								 required="required"/>
    <input type="checkbox" value="remember-me"/><label>아이디 저장</label>
	<label><small><%=msg==null?"":msg%></small></label>
	<button type="submit">Sign In</button>
	<button type="button" onclick="registForm()">Sign Up</button>
</form>

<script type="text/javascript">
	function registForm(){
		location.href="userController.jsp?command=registform";
	}
</script>
</body>
</html>
<%@ include file="footer.jsp" %>





