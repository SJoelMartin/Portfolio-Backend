package com.portfolio.contactBackend.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.contactBackend.dto.RefreshRequest;
import com.portfolio.contactBackend.dto.RegisterRequest;
import com.portfolio.contactBackend.model.Contact;
import com.portfolio.contactBackend.model.RefreshToken;
import com.portfolio.contactBackend.model.User;
import com.portfolio.contactBackend.repository.RefreshTokenRepository;
import com.portfolio.contactBackend.security.JwtUtil;
import com.portfolio.contactBackend.service.ContactService;
import com.portfolio.contactBackend.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactService contactService;
	
	//Get contacts with status
    @GetMapping
    public Page<Contact> getUsers(
    		Pageable pageable ,  
    		@RequestParam(required=false) String username
    		) {
    	return contactService.findByUsername(pageable,username);
    }
 
    //Register User    
    @PostMapping("/register")
    public ResponseEntity<?> register( @RequestBody RegisterRequest request) {
    	return userService.register(request.getUsername(), request.getPassword());   
    }
    
    //Grant Admin Role to User
    @PutMapping("/{username}/make-admin")
    public void updateRole( @PathVariable String username  ) {
    	userService.updateRole(username);
    }
    
    
}
