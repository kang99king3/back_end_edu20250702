<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 추가하기</title>
</head>
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
<h1>글 추가하기</h1>
<div id="myboard">
<form action="boardController.jsp" method="post">
<input type="hidden" name="command" value="insertboard"/> 
	<table border="1" class="table table-striped table-hover">
		<tr>
			<th>작성자(ID)</th>
			<td><input class="form-control" type="text" name="id" value="${ldto.tid}" readonly="readonly" required="required"/></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td><input class="form-control" type="text" name="title" required="required"/></td>
		</tr>
		<tr>
			<th>글내용</th>
			<td>
				<textarea class="form-control" rows="10" cols="60" name="content" 
				                          required="required"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input class="btn btn-primary" type="submit" value="글등록"/>
				<input class="btn btn-primary" type="button" value="글목록" 
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








