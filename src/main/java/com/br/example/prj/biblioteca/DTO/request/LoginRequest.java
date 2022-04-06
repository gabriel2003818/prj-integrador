package com.br.example.prj.biblioteca.DTO.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	
	@NotBlank
	@Email
	private String username;

	@NotBlank
	private String password;

}
