package com.portfolio.contactBackend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portfolio.contactBackend.dto.ApiResponse;
import com.portfolio.contactBackend.dto.AuthResponse;
import com.portfolio.contactBackend.exception.UserNotFoundException;
import com.portfolio.contactBackend.model.RefreshToken;
import com.portfolio.contactBackend.model.User;
import com.portfolio.contactBackend.model.User.Role;
import com.portfolio.contactBackend.repository.UserRepository;
import com.portfolio.contactBackend.security.JwtUtil;
import com.portfolio.contactBackend.specification.UserSpecification;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private RefreshTokenService refreshTokenService;
    
	public AuthResponse login(String username, String password) {
		System.out.println("Login Service");
        User user = userRepo.findByUsername(username);
        if( user == null ) throw new UserNotFoundException("User not found");
        
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String accessToken = jwtUtil.generateToken(user.getUsername(),user.getRole().name());
        
		RefreshToken refreshToken =
		        refreshTokenService.createRefreshToken(
		                user.getUsername()
		        );
		return new AuthResponse(
		        accessToken,
		        refreshToken.getToken(),
		        user.getRole()
		);
    }

	public Page<User> findUsers(Pageable pageable, String name, Role role) {
		Specification<User> spec = Specification
		        .where(UserSpecification.hasName(name))
		        .and(UserSpecification.hasRole(role));

		    return userRepo.findAll(spec, pageable);
	}
	
	public ResponseEntity<?> register( String username , String password ) {
		// check if username exists
        if (userRepo.findByUsername(username) != null) {
        	ApiResponse<?> response =
                    new ApiResponse<>(
                            false,
                            "Username already exists",
                            null
                    );
            return ResponseEntity
            		.status(HttpStatus.ALREADY_REPORTED)
                    .body(response);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword( encoder.encode(password));
        user.setRole(Role.GUEST);
        userRepo.save(user);
        return ResponseEntity.ok(
        		new ApiResponse<>(
		        true,
		        "User fetched successfully",
		        user
        		)
        	);
    }
	//Grant Admin Role	
	public void updateRole(String username) {
		User user = userRepo.findByUsername(username);
		user.setRole(Role.ADMIN);
	}

}
