package com.hk.ansboard.aop;

import org.aspectj.lang.JoinPoint;

// log 기록 기능--> 메서드 실행 기준
// Advice 구현 객체
public class LogExecute {

	/*
	 * JoinPoint에서 제공하는 메서드
	 * 		  - getTarget():대상 객체를 반환
	 * 		  - getArgs():메서드의 아규먼트를 반환
	 * 		  - toLongString():메서드의 모든 정보를 반환
	 * 		  - getSignature():호출되는 메서드에 대한 정보를 담고 있는 객체 반환
	 * 				-getName():메서드의 이름 구함
	 *  			-toLongName():메서드의 풀네임(리턴타입, 파라미터타입 모두 표시)
	 *  			-toShortName(): 메서드를 축약해서 표현(메서드의 이름나 표시)
	 */
	
	public void before(JoinPoint join) {
		
	}
	
	public void afterReturning(JoinPoint join) {
		
	}
	
	public void daoError(JoinPoint join) {
		
	}
}
