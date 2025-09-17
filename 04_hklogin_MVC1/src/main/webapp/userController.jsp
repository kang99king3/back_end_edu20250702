<%@page import="com.hk.dtos.UserDto"%>
<%@page import="com.hk.daos.UserDao"%>
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
	//요청값을 받기
	String command=request.getParameter("command");

	//싱글톤 패턴 : 클래스명.메서드 (static메서드 호출 방법)
	UserDao dao=UserDao.getUserDao();
	
	//어떤 요청인지 확인-> 해당코드를 실행
	if(command.equals("registform")){//회원가입폼이동
		response.sendRedirect("registform.jsp");
	}else if(command.equals("insertuser")){//회원가입하기
		//회원정보 파라미터 받기
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
// 		String address=request.getParameter("address");

		//주소 API 활용: 파라미터 받기 ----------------------
		String addr1=request.getParameter("addr1");//우편번호
		String addr2=request.getParameter("addr2");//기본주소
		String addr3=request.getParameter("addr3");//상세주소
		String addr4=request.getParameter("addr4");//참고항목
		String address=addr1+" "+addr2+" "+addr3+" "+addr4;
		//주소 API 활용 종료-------------------------------
		
		String email=request.getParameter("email");
		
		boolean isS=dao.insertUser(
				   new UserDto(id,name,password,address,email));
		if(isS){
			%>
			<script type="text/javascript">
				alert("회원에 가입이 되셨습니다.");
				location.href="index.jsp";
			</script>
			<%
		}else{
			%>
			<script type="text/javascript">
				alert("회원가입실패");
				location.href="error.jsp";
			</script>
			<%
		}
	}else if(command.equals("idchk")){//id체크하기
		String id=request.getParameter("id");
		String resultId=dao.idCheck(id);//결과값이 null이면 사용가능
		
		request.setAttribute("resultId", resultId);
		pageContext.forward("idchkform.jsp"); 
	}
%>
</body>
</html>









