package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.AdminDao;
import com.hk.dtos.UserDto;

@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session=request.getSession();
		
		String requestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		
		//command 값 구하기 --> "/boardlist.board" 추출
		String command=requestURI.substring(contextPath.length());
		System.out.println("요청Command:"+command);
		
		AdminDao dao=AdminDao.getAdminDao();
		
		if(command.equals("/userlistall.admin")){
			List<UserDto>list=dao.getAllUserList();
			request.setAttribute("list", list);
//			pageContext.forward("admin_listAll.jsp");
			dispatch("admin_listAll.jsp", request, response);
		}else if(command.equals("/userlist.admin")){
			List<UserDto>list=dao.getUserList();
			request.setAttribute("list", list);
//			pageContext.forward("admin_list.jsp");
			dispatch("admin_list.jsp", request, response);
		}else if(command.equals("/userrole.admin")){
			String id=request.getParameter("id");
			UserDto dto=dao.getUserRole(id);
			request.setAttribute("dto", dto);
//			pageContext.forward("admin_userRoleForm.jsp");
			dispatch("admin_userRoleForm.jsp", request, response);
		}else if(command.equals("/updaterole.admin")){
			String id=request.getParameter("id");
			String role=request.getParameter("role");
			boolean isS=dao.getUpdateRole(id,role);
			if(isS){
				response.sendRedirect("userlist.admin");
			}else{
				response.sendRedirect("error.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//forward 기능 구현
			public void dispatch(String url,
								 HttpServletRequest request, 
					             HttpServletResponse response) throws ServletException, IOException {
				request.getRequestDispatcher(url)
			       .forward(request, response);
			}
			
			public void jsResponse(String url, String msg,
								   HttpServletResponse response) 
								   throws IOException {
				PrintWriter out=response.getWriter();
				String js=
				 "<script type='text/javascript'>"
				+"	alert('"+msg+"');"
				+"	location.href='"+url+"';"
				+"</script>" ;
				
				out.print(js);//브라우저로 출력한다.
			}
}
