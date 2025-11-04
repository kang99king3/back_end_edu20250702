package com.hk.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
	@NotBlank
	@Size(min = 3, max = 20)
	private String id;

	@NotBlank
	@Size(min = 6, max = 100)
	private String password;

	@NotBlank
	private String name;

	@NotBlank
	private String address;

	@NotBlank
	@Email
	private String email;
}
