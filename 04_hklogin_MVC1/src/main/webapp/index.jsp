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
<form action="userController.jsp" method="post">
	<input type="hidden" name="command" value="login">
	<h1>Login</h1>
	<input type="text" name="id" placeholder="ID" 
								 required="required"/>
	<input type="password" name="password" 
								 placeholder="password" 
								 required="required"/>
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





