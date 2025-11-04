package com.hk.board.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hk.board.dto.MemberResponseDto;
import com.hk.board.entity.Member;
import com.hk.board.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(member.getId())
                .password(member.getPassword()) // 이미 DB에 암호화 저장된 비밀번호 사용
                .roles(member.getRole())
                .build();
	}

}
