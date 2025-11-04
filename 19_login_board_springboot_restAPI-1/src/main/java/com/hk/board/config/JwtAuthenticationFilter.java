package com.hk.board.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//JWT 인증 필터
//모든 요청마다 1회 실행되어 (OncePerRequestFilter 상속)
//JWT 토큰을 확인하고 인증 객체를 SecurityContext에 설정하는 역할을 수행
public class JwtAuthenticationFilter extends OncePerRequestFilter {

 private final JwtUtils jwtUtils;                 // JWT 생성 및 검증 유틸리티
 private final UserDetailsService userDetailsService; // 사용자 정보를 로드하기 위한 서비스

 // 생성자 주입: SecurityConfig에서 Bean 등록 시 주입됨
 public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
     this.jwtUtils = jwtUtils;
     this.userDetailsService = userDetailsService;
 }

 @Override
 protected void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain)
         throws ServletException, IOException {

     // 클라이언트 요청 헤더에서 Authorization 정보 추출
     String authHeader = request.getHeader("Authorization");
     String token = null;
     String username = null;

     // 헤더가 존재하고 'Bearer '로 시작하는 경우만 처리
     if (authHeader != null && authHeader.startsWith("Bearer ")) {
         token = authHeader.substring(7);         // "Bearer " 이후의 토큰 문자열 추출
         username = jwtUtils.getUsernameFromJwt(token); // 토큰에서 사용자 이름 추출
     }

     // username이 존재하고, 현재 SecurityContext에 인증 정보가 없는 경우 (중복 방지)
     if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

         // DB나 메모리에서 사용자 정보를 로드 (UserDetails 객체 반환)
         UserDetails userDetails = userDetailsService.loadUserByUsername(username);

         // 토큰이 유효하고, 토큰에 포함된 사용자와 DB 사용자 정보가 일치하면 인증 처리
         if (jwtUtils.validateJwt(token) && username.equals(userDetails.getUsername())) {

             // SecurityContext에 저장할 Authentication 객체 생성
             UsernamePasswordAuthenticationToken authToken =
                     new UsernamePasswordAuthenticationToken(
                             userDetails,             // 인증된 사용자 정보
                             null,                    // 비밀번호는 null (이미 인증됨)
                             userDetails.getAuthorities()); // 권한 정보(Role 등)

             // 요청 정보를 Authentication 객체에 추가 (IP, 세션 정보 등)
             authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             // SecurityContext에 Authentication 저장
             // 이후 컨트롤러에서 Authentication 객체로 사용자 정보 접근 가능
             SecurityContextHolder.getContext().setAuthentication(authToken);
         }
     }

     // 다음 필터로 요청을 넘김 (JWT 검증 이후 Spring Security의 나머지 필터 체인 실행)
     filterChain.doFilter(request, response);
 }
}

