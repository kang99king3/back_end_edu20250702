package com.hk.board.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.board.config.JwtUtils;
import com.hk.board.controller.MemberController;
import com.hk.board.dto.LoginRequestDto;
import com.hk.board.dto.MemberResponseDto;
import com.hk.board.dto.MessageResponse;
import com.hk.board.dto.SignupRequestDto;
import com.hk.board.entity.Member;
import com.hk.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;

	public ResponseEntity<?> login(LoginRequestDto req) {
        Optional<Member> optionalMember = memberRepository.findByLoginId(req.getId());

        if (optionalMember.isEmpty()) {
            return ResponseEntity.status(401).body(new MessageResponse("Invalid credentials"));
        }

        Member member = optionalMember.get();

        if (!passwordEncoder.matches(req.getPassword(), member.getPassword())) {
            return ResponseEntity.status(401).body(new MessageResponse("Invalid credentials"));
        }

        //token 발급
        String token = jwtUtils.generateToken(member.getId());
        System.out.println("token:"+token);
        return ResponseEntity.ok(Map.of("token", token));//Map.of()는 new HashMap(),map.put() 간소화
    }
	
	public MemberResponseDto findByLoginId(String id) {
        return memberRepository.findByLoginId(id)
                .map(member -> new MemberResponseDto(
                        member.getId(),
                        member.getName(),
                        member.getAddress(),
                        member.getEmail(),
                        member.getRole()
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
	 
	@Transactional
	public MemberResponseDto register(SignupRequestDto req) {
		Member m = Member.builder()
		.id(req.getId())
		.password(passwordEncoder.encode(req.getPassword()))
		.name(req.getName())
		.address(req.getAddress())
		.email(req.getEmail())
		.role("USER")
		.build();
		
		Member saved = memberRepository.save(m);
		System.out.println(saved);
		return new MemberResponseDto(
		        saved.getId(),
		        saved.getName(),
		        saved.getAddress(),
		        saved.getEmail(),
		        saved.getRole()
		    );
	}
}
