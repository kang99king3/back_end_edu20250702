package com.hk.board.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberResponseDto {

	private String id;
	private String name;
	private String address;
	private String email;
	private String role;

}
