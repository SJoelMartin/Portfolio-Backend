package com.portfolio.contactBackend.dto;

public class RefreshRequest {
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String token) {
		this.refreshToken = token;
	}
	
}
