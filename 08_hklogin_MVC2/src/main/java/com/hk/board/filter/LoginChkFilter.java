package com.hk.board.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginChkFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //false: 요청과 연결된 세션이 있으면 있는거 쓰고, 없으면 새로 생성해라
        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();

        // 로그인이 필요 없는 페이지 처리 : /cust/board  -> /cust/* ,  /admin/*
        if (uri.endsWith("registform.jsp") ||
        	uri.endsWith("registform.user")||	
        	uri.endsWith("index.jsp")||
        	uri.endsWith("error.jsp")||
        	uri.endsWith("idchk.user")||
        	uri.endsWith("login.user")
  
        	) {
            chain.doFilter(request, response);
            return;
        }

        // 로그인 체크
        if (session == null || session.getAttribute("ldto") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // 캐시 방지 (로그아웃 후 뒤로가기 방지)
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
