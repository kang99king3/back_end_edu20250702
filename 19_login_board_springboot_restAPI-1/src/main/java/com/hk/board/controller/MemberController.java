package com.hk.board.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hk.board.config.JwtUtils;
import com.hk.board.dto.LoginRequestDto;
import com.hk.board.dto.MemberResponseDto;
import com.hk.board.dto.MessageResponse;
import com.hk.board.dto.SignupRequestDto;
import com.hk.board.entity.Member;
import com.hk.board.repository.MemberRepository;
import com.hk.board.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class MemberController {

    private final AuthenticationManager authenticationManager;
	
	private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto req) {
        return memberService.login(req);
    }

    // (선택) 인증된 사용자 정보 반환 (Authorization: Bearer <token> 필요)
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).body(new MessageResponse("Unauthorized"));
        }

        String id = authentication.getName();
        MemberResponseDto userDto=memberService.findByLoginId(id);
        return ResponseEntity.ok(userDto);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
    	MemberResponseDto dto=memberService.register(requestDto);
    	System.out.println(dto);
        return ResponseEntity.ok(dto);
    }

}
