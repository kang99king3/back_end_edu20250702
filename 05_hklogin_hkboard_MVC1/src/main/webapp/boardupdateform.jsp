<%@ include file="header.jsp" %>
<%@page import="com.hk.dtos.HkDto"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 수정 폼</title>
</head>
<%
	//controller.jsp에서 구한 DTO객체 requestScope("dto",DTO);
	// forward()로 이동하면 DTO객체가 전달된다.
	HkDto dto=(HkDto)request.getAttribute("dto");
%>
<body>
<div id="container">
	<div class="main">
		<div>
			<span>
<%-- 			<%=ldto.getId()%>[<%=ldto.getRole()%>] --%>
			${sessionScope.ldto.tid}[${sessionScope.ldto.trole}]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="userController.jsp?command=userinfo&id=${ldto.tid}">나의 정보</a>
			</span>|
			<span>
				<a href="boardController.jsp?command=boardlist">게시판</a>
			</span>|
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
	</div>
	<div class="content">
	<div id="myboard">
<h1>게시판 수정하기</h1>
<form action="boardController.jsp" method="post">
<input type="hidden" name="command" value="boardupdate">
<input type="hidden" name="seq" value="<%=dto.getTseq()%>"/>
<table border="1" class="table  table-striped table-hover">
	<tr>
		<th>작성자(ID)</th>
		<td><%=dto.getTid()%></td>
	</tr>
	<tr>
		<th>글제목</th>
		<td><input class="form-control" type="text" name="title" 
							   value="<%=dto.getTtitle()%>"/></td>
	</tr>
	<tr>
		<th>글내용</th>
		<td>
			<textarea class="form-control" rows="10" cols="60" name="content" ><%=dto.getTcontent()%></textarea>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정완료" />
			<input type="button" value="글목록" 
			  onclick="location.href='boardController.jsp?command=boardlist'"/>
		</td>
	</tr>
</table>
</form>
</div>
</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>