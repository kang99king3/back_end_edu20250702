package com.hk.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hk.board.dto.MemberResponseDto;
import com.hk.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	@Query("SELECT m FROM Member m WHERE m.id = :id")
	Optional<Member> findByLoginId(@Param("id") String id);
	boolean existsById(String id);
	boolean existsByEmail(String email);
	
}
