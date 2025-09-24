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
<title>게시판 상세보기</title>
</head>
<%
	//controller에서 request 스코프에 ["dto":dto] 객체를 저장했음
	// boarddetail.jsp로 전달이 되고 아래 코드처럼 받는다
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
		<h1>게시판 상세보기</h1>
		<div id="myinfo">
		<table border="1" class="table  table-striped table-hover">
			<tr>
				<th>작성자(ID)</th>
				<td><%=dto.getTid()%></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><%=dto.getTtitle()%></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<textarea class="form-control" rows="10" cols="60" name="content" 
					  readonly="readonly"><%=dto.getTcontent()%></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<%
						if(ldto.getTid().equals(dto.getTid())){
					%>
					<input class="btn btn-primary" type="button" value="수정폼이동"
										 onclick="updateForm('<%=dto.getTseq()%>')"/>
										 
					<input class="btn btn-primary" type="button" value="삭제"
										 onclick="delBoard('<%=dto.getTseq()%>')"/>
					<%
						}
					%>										 
					<input class="btn btn-primary" type="button" value="글목록" 
								onclick="location.href='boardController.jsp?command=boardlist'"/>
				</td>
			</tr>
		</table>
		</div>
	</div>
</div>
<script type="text/javascript">

	// 필요한 파라미터 pk값
	// boardupdateform.jsp, boardupdate.jsp
	function updateForm(seq){
		// 수정폼 이동-> 수정폼에서는 글의 상세내용 보여주고, 
		//             수정완료버튼클릭하면 수정되게 처리(제목,내용만 수정)
		location.href="boardController.jsp?command=boardupdateform&seq="+seq;
	}
	// boardDelete.jsp
	function delBoard(seq){
		location.href="boardController.jsp?command=boarddelete&seq="+seq;
	}
</script>
</body>
</html>
<%@ include file="footer.jsp" %>




