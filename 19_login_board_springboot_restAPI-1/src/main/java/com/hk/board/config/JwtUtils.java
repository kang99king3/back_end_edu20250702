package com.hk.board.config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//JWT 관련 유틸리티 클래스
//토큰 생성, 검증, 정보 추출 기능 담당
@Component
public class JwtUtils {

	private final Key key;           // 서명(Signature)을 위한 비밀 키
	private final long expirationMs; // 토큰 유효기간 (밀리초 단위)

	// 생성자: application.properties에서 jwt.secret, jwt.expirationMs 값을 주입받음
	public JwtUtils(@Value("${jwt.secret}") String secret,
			        @Value("${jwt.expirationMs}") long expirationMs) {
		// 비밀 키 문자열을 바이트 배열로 변환 후, HMAC SHA256 알고리즘용 Key 객체 생성
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.expirationMs = expirationMs;
	}
	
	// JWT 토큰 생성 메서드
	public String generateToken(String username) {
		Date now = new Date();                         // 현재 시간
		Date exp = new Date(now.getTime() + expirationMs); // 만료 시간 계산

		// JWT 구성요소 생성
		// - Subject: 사용자 이름 (username)
		// - IssuedAt: 발급 시간
		// - Expiration: 만료 시간
		// - SignWith: HMAC-SHA256 알고리즘으로 서명
		return Jwts.builder()
			.setSubject(username)
			.setIssuedAt(now)
			.setExpiration(exp)
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();  // JWT 문자열로 직렬화
	}

	// JWT로부터 사용자 ID(또는 username) 추출
	public String getUsernameFromJwt(String token) {
		// 토큰 파싱 → Claims(페이로드) 추출 → subject(username) 반환
		return Jwts.parserBuilder()
			.setSigningKey(key)     // 서명 검증용 비밀 키 설정
			.build()
			.parseClaimsJws(token)  // 토큰 파싱 및 검증
			.getBody()
			.getSubject();          // 사용자 식별값 반환
	}

	// 토큰이 형식상 유효한지(서명 검증)만 확인하는 메서드
	public boolean validateJwt(String token) {
		try {
			// 서명 키로 JWT 파싱 시 오류가 없으면 유효한 토큰
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			// 토큰이 손상되었거나 만료, 변조된 경우 false 반환
			return false;
		}
	}
	
	// 토큰이 해당 사용자에게 유효한지 확인
	public boolean validateToken(String token, UserDetails userDetails) {
	    String username = getUsernameFromJwt(token);
	    // 토큰의 username과 UserDetails의 username이 일치하고, 만료되지 않았는지 확인
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// 토큰 만료 여부 검사
	private boolean isTokenExpired(String token) {
	    Date expiration = Jwts.parserBuilder()
	                          .setSigningKey(key)
	                          .build()
	                          .parseClaimsJws(token)
	                          .getBody()
	                          .getExpiration(); // 토큰의 만료 시간 추출

	    return expiration.before(new Date()); // 현재 시간과 비교 → 만료되었으면 true
	}
}
