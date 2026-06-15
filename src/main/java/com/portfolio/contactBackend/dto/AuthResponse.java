package com.portfolio.contactBackend.dto;

import com.portfolio.contactBackend.model.User.Role;

public class AuthResponse {

    private String accessToken;
    private String refreshToken;
    private Role role;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public AuthResponse(String accessToken, String refreshToken , Role role) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.role = role;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}