package com.portfolio.contactBackend.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.portfolio.contactBackend.model.Contact;
import com.portfolio.contactBackend.model.Contact.Status;
import com.portfolio.contactBackend.service.*;

import jakarta.validation.*;

@RestController
@Validated
@CrossOrigin(origins = "*",
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
allowedHeaders = "*" ) // important for frontend connection
@RequestMapping("/api/contact")
public class ContactController {
	
    private final ContactService service;
    
    public ContactController( ContactService service ){
    	this.service = service;
    }
    
    @PostMapping("/submit")
    public void saveContact(@Valid @RequestBody Contact contact) {
    	service.addContact(contact);
    }
    
    //Get contacts with status
    @GetMapping
    public Page<Contact> getContacts(
    		Pageable pageable ,  
    		@RequestParam(required=false) Status status,
    		@RequestParam(required=false) String name) {
    	System.out.println(status);
    	System.out.println(name);
        return service.findContacts(pageable,name,status);
    }
    
    //Update status of contacts
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam Status status) {
    	System.out.println("ID: " + id + " STATUS: " + status);
        Contact contact = service.updateStatus(id,status);
        return ResponseEntity.ok(contact);
    }
    
    //Update contact message
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void updateMessage(@PathVariable Long id , @RequestBody Map<String, String> body) {
    	String message = body.get("message");
    	service.updateMessage(id,message);
    }
    
    //Delete Contact Record    
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id) {
    	service.deleteEntry(id);
    }	
}