package com.br.example.prj.biblioteca.DTO.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String rg;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, String rg, String email, List<String> roles) {
		this.token = accessToken;
		this.rg = rg;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}
}
