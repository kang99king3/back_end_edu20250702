package com.hk.stock.realtime.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 웹소켓 + STOMP 메시지 브로커를 쓰겠다고 선언
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		 registry.addEndpoint("/ws")               // WebSocket 엔드포인트: JS에서 new SockJS("/ws"); 요청하면 연결하겠다는 의미
         .setAllowedOriginPatterns("*")    // CORS 허용 (필요시 도메인 제한)
         .withSockJS();                    // SockJS fallback : 브라우저가 웹소켓을 지원 안해도 가능하게 해줌
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");      // 구독 prefix
        registry.setApplicationDestinationPrefixes("/app"); // 메시지 전송 prefix: 클라이언트에서 서버로 메시지 전송할때 
	}
}