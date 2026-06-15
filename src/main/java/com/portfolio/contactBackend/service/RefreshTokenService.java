package com.portfolio.contactBackend.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.contactBackend.exception.UserUnauthorizedException;
import com.portfolio.contactBackend.model.RefreshToken;
import com.portfolio.contactBackend.model.User;
import com.portfolio.contactBackend.repository.RefreshTokenRepository;
import com.portfolio.contactBackend.repository.UserRepository;
import com.portfolio.contactBackend.security.JwtUtil;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshRepo;

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public RefreshToken createRefreshToken(String username) {
    	
    	System.out.println("Refresh Token Created");
    	
        User user = userRepo.findByUsername(username);
        refreshRepo.deleteByUser(user);
        refreshRepo.flush();
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(
                LocalDateTime.now().plusHours(10)
        );
        System.out.println("Refresh Token : " + token.getToken() );
        return refreshRepo.save(token);
    }
    
    public ResponseEntity<?> refreshToken( String requestToken ){
 
        RefreshToken refreshToken = refreshRepo
                .findByToken(requestToken)
                .orElseThrow(() ->
                        new UserUnauthorizedException("Invalid refresh token"));

        if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
        	System.out.println("Refresh Token Expired");
            refreshRepo.delete(refreshToken);

            throw new UserUnauthorizedException("Refresh token expired");
        }
        User user = refreshToken.getUser();
	    refreshRepo.delete(refreshToken);
	    RefreshToken newRefresh = createNewRefreshToken( refreshToken );
	
	    String accessToken = jwtUtil.generateToken( user.getUsername(), user.getRole().name() );
	
	    return ResponseEntity.ok(
	         Map.of(
	             "accessToken", accessToken,
	             "refreshToken", newRefresh.getToken()
	         )
	    );
    }
    
    public RefreshToken createNewRefreshToken(RefreshToken refreshToken) {
    	
        refreshToken.setToken(UUID.randomUUID().toString());
    	System.out.println("New Refresh Token: " +refreshToken.getToken());

        return refreshToken;
    }
}