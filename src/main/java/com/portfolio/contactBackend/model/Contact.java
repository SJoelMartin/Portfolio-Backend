package com.portfolio.contactBackend.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "contact_details")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="Name is required")
    private String name;
    
    @NotBlank(message="Email is required")
    @Email(message="Please enter a valid email address")
    private String email;
    
    private String password;
    
    @NotBlank(message="Message is required")
    private String message;
    
    @CreationTimestamp
    @Column(updatable=false)
    private LocalDateTime createdDate;
    
    @CreationTimestamp
    private LocalDateTime updatedDate;
    
    public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Enumerated(EnumType.STRING)
    private Status status = Status.ALL;

    public enum Status  {
    	ALL,
    	NEW,
    	READ,
    	RESOLVED
    }
    
    @ManyToOne
    private User user;
    
    // Default constructor (IMPORTANT)
    public Contact() {}

    // Parameterized constructor
    public Contact(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
	public void setCreatedDate(LocalDateTime date) {
		this.createdDate = date;
	}
    
    public Status getStatus() {
    	return status;
    }
    public void setStatus( Status status ) {
    	this.status = status;
    }

	public  String getPassword() {
		return password;
	}

}