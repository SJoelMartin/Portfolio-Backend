package com.portfolio.contactBackend.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.portfolio.contactBackend.dto.ApiResponse;
import com.portfolio.contactBackend.exception.UserNotFoundException;
import com.portfolio.contactBackend.exception.UserUnauthorizedException;
import com.portfolio.contactBackend.model.Contact;
import com.portfolio.contactBackend.model.Contact.Status;
import com.portfolio.contactBackend.model.User;
import com.portfolio.contactBackend.repository.ContactRepository;
import com.portfolio.contactBackend.specification.ContactSpecification;

@Service
public class ContactService {

    private final ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }
    
    public void addContact( Contact contact ) {
    	contact.setStatus(Status.NEW);
    	contact.setUpdatedDate(LocalDateTime.now());
    	repository.save(contact);
    }

	public Page<Contact> findContacts(Pageable pageable, String name  , Status status ) {
	    Specification<Contact> spec = Specification
	        .where(ContactSpecification.hasName(name))
	        .and(ContactSpecification.hasStatus(status));

	    Page<Contact> page = repository.findAll(spec, pageable);
	    
	    return page;
//		Search without specification
//		if( status == null || status == Status.ALL ) {
//			if( name == null || name.equals("") ) {
//				return repository.findAll(pageable);
//			}
//			else {
//				return repository.findByNameContainingIgnoreCase(pageable, name);
//			}
//		}
//		else if( name == null || name.equals("") ) {
//			return repository.findByStatus(pageable,status);
//		}
//		return repository.findByNameContainingIgnoreCaseAndStatus(pageable, name, status);
	}

	public Contact updateStatus(Long id , Status status) {
		Contact contact = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Not found"));
		contact.setStatus(status);
		return repository.save(contact);
	}

	public Page<Contact> findByUsername(Pageable pageable, String username) {
		Specification<Contact> spec = Specification
		        .where(ContactSpecification.hasName(username));
		return repository.findAll(spec,pageable);
	}

	public ResponseEntity<?> updateMessage(Long id , String message) {
		Contact contact = repository.findById(id)
				.orElseThrow(()->new UserNotFoundException("User message not found"));
		contact.setMessage(message);
		contact.setUpdatedDate(LocalDateTime.now());
		repository.save(contact);
		return ResponseEntity.ok(
				new ApiResponse<>(
				        true,
				        "Message Updated successfully",
				        contact
				    )
				);
	}

	
	public void deleteEntry(Long id) {
		repository.deleteById(id);
	}

	

}