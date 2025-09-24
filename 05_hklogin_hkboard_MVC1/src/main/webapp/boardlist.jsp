<%@ include file="header.jsp" %>
<%@page import="com.hk.dtos.HkDto"%>
<%@page import="java.util.List"%>
<%@page import="com.hk.daos.HkDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<%response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록 조회</title>
<script type="text/javascript">
	function insertBoardForm(){
		location.href=
	        "boardController.jsp?command=insertboardform";
	}
	
</script>
</head>
<%
	//request 스코프에 객체를 저장하면 모두 Object로 강제 형변환됨
// 	Object obj=request.getAttribute("list");
// 	List<HkDto> list=(List<HkDto>)obj;
	List<HkDto> list=
			(List<HkDto>)request.getAttribute("list");
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
<h1>게시판</h1>
<div id="myboard">
<h2>글목록</h2>
<table border="1" class="table  table-striped table-hover">
	<tr>
		<td colspan="4" class="text-end">
			<form action="boardController.jsp" method="get" class="d-inline-flex align-items-center">
			    <input type="hidden" name="command" value="searchid"/>
			    <label class="form-label me-2 mb-0">아이디:</label>
			    <% String id = request.getParameter("id");%>
			    <input class="form-control me-2" type="text" name="id" 
			           value="<%=id != null ? id : ""%>" style="max-width:200px;"/>
			    <button class="btn btn-primary" type="submit">검색</button>
			</form>
		</td>
	</tr>
	<tr>
		<th>번호</th><th>작성자</th><th>제목</th><th>작성일</th>
	</tr>
	<%
		for(HkDto dto:list){
			%>
			<tr>
				<td><%=dto.getTseq()%></td>
				<td><%=dto.getTid()%></td>
				<td>
				<%
					if(dto.getDelflag().equals("Y")){	
						out.print("--- 삭제된 글입니다. ---");
					}else{
						%>
						<a href="boardController.jsp?command=boarddetail&seq=<%=dto.getTseq()%>"><%=dto.getTtitle()%></a></td>						
						<%
					}
				%>
				<td><%=dto.getTregdate()%></td>
			</tr>
			<%
		}
	%>
	<tr>
		<td colspan="4">
			<button type="button" onclick="insertBoardForm()" class="btn btn-primary">글추가</button>	
		</td>
	</tr>
</table>
	</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>







