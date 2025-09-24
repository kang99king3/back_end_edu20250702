<%@page import="java.util.List"%>
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
<%
	List<UserDto> list=(List<UserDto>)request.getAttribute("list");
%>
<body>
<div id="container">
	<div class="main">
		<div>
			<span>
			<%=ldto.getTid()%>[<%=ldto.getTrole()%>]
			님이 로그인 하였습니다.
			</span>
			<span>
				<a href="adminController.jsp?command=userlistall">회원전체조회</a>
			</span>|
			<span>
				<a href="adminController.jsp?command=userlist">회원정보[등급]수정</a>
			</span>|
			<span>
				<a href="userController.jsp?command=logout">로그아웃</a>
			</span>
		</div>
	</div>
	<div class="admin_content">
		<h1>관리자 페이지</h1>
		<div class="userlist">
			<table border="1" class="table  table-striped table-hover">
				<tr class="table-primary">                  
					<th>회원번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>회원등급</th>
					<th>가입일</th>
				</tr>
				<%	int i=1;
					for(UserDto dto:list){
						%>
						<tr>
							<td><%=i%></td>
							<td><%=dto.getTid()%></td>
							<td><%=dto.getTname()%></td>
							<td><%=dto.getTrole()%>
								<%  //로그인한 아이디와 목록에 아이디가 다를 경우만 변경버튼 출력
									if(!(dto.getTid().equals(ldto.getTid()))){
										%>										
										<a href="adminController.jsp?command=userrole&id=<%=dto.getTid()%>" 
										   class="btn btn-info" style="margin-left: 20px;font-size: 10px;font-weight: bold;">변경</a>
										<%
									}
								%>
							</td>
							<td><%=dto.getRegDate()%></td>
						</tr>						
						<%
						i++;
					}
				%>
			</table>
		</div>
	</div>
</div>
</body>
</html>
<%@ include file="footer.jsp" %>