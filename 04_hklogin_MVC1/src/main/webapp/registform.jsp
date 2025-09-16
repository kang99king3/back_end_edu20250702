<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입폼</title>
<link rel="stylesheet" href="css/registForm.css" >
<script type="text/javascript">
	//아이디 중복체크
	function idChk(){
		const id=document
		        .querySelectorAll("input[name=id]")[0].value;
		if(id==""){
			alert("아이디를 입력하세요");
		}else{
			window.open("userController.jsp?command=idchk&id="+id,
					    "아이디 확인","width=300px,height=300px");
		}
	}
</script>
</head>
<body>
<div id="registForm">
	<h1>회원가입</h1>
	<form action="userController.jsp" method="post">
		<input type="hidden" name="command" value="insertuser" />
		<input type="text" name="id" required="required" placeholder="ID"/>
		<button onclick="idChk()">중복체크</button>
		<input type="text" name="name" required="required" placeholder="NAME" />
		<input type="password" name="password" required="required"placeholder="PASSWORD" />
		<input type="password" name="password2" required="required" placeholder="PASSWORD확인"/>
		<input type="text" name="address" required="required"placeholder="ADDRESS" />
		<input type="email" name="email" required="required" placeholder="EMAIL"/>
		<button type="submit">가입완료</button>
		<button type="button" onclick="location.href='index.jsp'">메인</button>
	</form>
</div>
</body>
</html>






