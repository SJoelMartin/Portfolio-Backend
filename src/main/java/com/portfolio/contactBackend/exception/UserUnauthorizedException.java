package com.portfolio.contactBackend.exception;

public class UserUnauthorizedException extends RuntimeException{
	public UserUnauthorizedException(String message) {
        super(message);
    }
}
