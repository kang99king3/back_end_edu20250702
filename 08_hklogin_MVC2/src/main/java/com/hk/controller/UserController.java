package com.hk.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hk.daos.UserDao;
import com.hk.dtos.UserDto;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Client A라는 사람 pc(jsessionid) ---> (jsessionid)session 
		//        B라는 사람 pc(jsessionid) ---> (jsessionid)session
		HttpSession session=request.getSession();//세션객체 생성
		
		// 08_login_MVC2/boardlist.board
		String requestURI=request.getRequestURI();
		// 08_login_MVC2
		String contextPath=request.getContextPath();
		
		//08_login_MVC2/boardlist.board
		//command 값 구하기 --> "/boardlist.board" 추출
		String command=requestURI.substring(contextPath.length());
		System.out.println("요청Command:"+command);
		
		UserDao dao=UserDao.getUserDao();
		
		if(command.equals("/registform.user")){//회원가입폼이동
			response.sendRedirect("registform.jsp");
		}else if(command.equals("/insertuser.user")){//회원가입하기
			//회원정보 파라미터 받기
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String password=request.getParameter("password");
//	 		String address=request.getParameter("address");

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
				jsResponse("index.jsp", "회원에 가입이 되셨습니다.", response);
			}else{
				jsResponse("error.jsp", "회원가입실패", response);
			}
		}else if(command.equals("/idchk.user")){//id체크하기
			String id=request.getParameter("id");
			String resultId=dao.idCheck(id);//결과값이 null이면 사용가능
			
			request.setAttribute("resultId", resultId);
			dispatch("idchkform.jsp", request, response);
		}else if(command.equals("/login.user")){
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			
			//id와 pw에 해당하는 회원정보 조회
			UserDto dto=dao.getLogin(id, password);
			
			if(dto==null||dto.getId()==null){//회원이 존재하지 않는다면
				response.sendRedirect("index.jsp?msg="
				      +URLEncoder.encode("회원가입을 해주세요","utf-8"));
			}else{
				//회원이라면!
				//sessionScope객체에 로그인 정보를 저장
				session.setAttribute("ldto", dto);
				session.setMaxInactiveInterval(10*60);//10분간 유지
				
				//등급[ADMIN,MANAGER,USER]을 확인해서 해당 Main페이지로 이동하기
				if(dto.getRole().toUpperCase().equals("ADMIN")){
					response.sendRedirect("admin_main.jsp");
				}else if(dto.getRole().toUpperCase().equals("MANAGER")){
					response.sendRedirect("manager_main.jsp");
				}else if(dto.getRole().toUpperCase().equals("USER")){
					response.sendRedirect("user_main.jsp");
				}
			}
		}else if(command.equals("/logout.user")){//로그아웃하기
			//session의 로그인 정보를 삭제한다.
//	 		session.removeAttribute("ldto");// ldto만 삭제
			session.invalidate();//session의 모든 정보 삭제
			response.sendRedirect("index.jsp");
		}else if(command.equals("/userinfo.user")){//회원상세조회
			//로그인할때 select문 모든 정보조회로 구현
			// --> 나의정보 조회할때도 활용하는 경우가 있음
			//    --> sessoin에 저장하고 그 정보를 사용 --> 이렇게 사용하면 안됨
			
			String id=request.getParameter("id");
			UserDto dto=dao.getUser(id);
			
			//객체(dto)를 스코프객체에 저장하고
			request.setAttribute("dto", dto);
			//이동한다.
			
			dispatch("userinfo.jsp", request, response);
		}else if(command.equals("/userupdate.user")){
			String id=request.getParameter("id");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			
			boolean isS=dao.updateUser(new UserDto(id,address,email));
			
			if(isS){
				jsResponse("userinfo.user?id="+id, "수정완료", response);
			}else{
				jsResponse("error.jsp", "수정실패", response);
			}
		}else if(command.equals("/deluser.user")){
			String id=request.getParameter("id");
			
			boolean isS=dao.delUser(id);
			session.invalidate();//세션 삭제
			if(isS){
				jsResponse("index.jsp", "회원 탈퇴를 완료하였습니다", response);
			}else{
				jsResponse("index.jsp", "회원탈퇴실패", response);
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
