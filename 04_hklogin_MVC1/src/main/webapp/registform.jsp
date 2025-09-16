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
	
	onload=function(){
		const inputs=document.querySelectorAll("input[name]");
		
		for (let i = 2; i < inputs.length; i++) {
			inputs[i].onfocus=function(){
			let isIdChk=localStorage.getItem("idchk");
				if(isIdChk==null||isIdChk=='n'){
					alert("아이디 중복체크를 먼저 확인하세요");
					inputs[1].focus();//ID입력박스로 이동
				}
			}
		}
	}
	//form 전송시 패스워드가 맞는지 확인후 가입 진행
	function isPW(formEle){
		
		//password, password2의 값이 동일한지 확인
		if(formEle.password.value!=formEle.password2.value){
			alert("비밀번호를 확인하세요");
			formEle.password.value="";
			formEle.password2.value="";
			formEle.password.focus();
			return false;//전송 취소시킴
		}
		
		localStorage.removeItem("idchk");//storage의 값을 삭제한다.
		return true;
	}
</script>
</head>
<body>
<div id="registForm">
	<h1>회원가입</h1>
	<form action="userController.jsp" method="post" onsubmit="return isPW(this)">
		<input type="hidden" name="command" value="insertuser" />
		<input type="text" name="id" required="required" placeholder="ID"/>
		<button type="button" onclick="idChk()">중복체크</button>
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






