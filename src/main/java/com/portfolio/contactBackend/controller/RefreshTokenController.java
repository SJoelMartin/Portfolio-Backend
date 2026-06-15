package com.portfolio.contactBackend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.contactBackend.dto.RefreshRequest;
import com.portfolio.contactBackend.service.RefreshTokenService;

@RestController
@RequestMapping("/api/user")
public class RefreshTokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken( @RequestBody RefreshRequest request) {
    	try {
    		return refreshTokenService.refreshToken(request.getRefreshToken());
    	}
    	catch( Exception e ) {
    		return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("error", e.getMessage()));
    	}
    }
}
