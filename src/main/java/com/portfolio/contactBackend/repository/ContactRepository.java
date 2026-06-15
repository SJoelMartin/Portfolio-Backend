package com.portfolio.contactBackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.portfolio.contactBackend.model.Contact;
import com.portfolio.contactBackend.model.Contact.Status;

public interface ContactRepository extends JpaRepository<Contact, Long> , JpaSpecificationExecutor<Contact> {

	Contact findByName(String name);
//	Methods before specification
//	Page<Contact> findByStatus(Pageable pageable , Status status);
//	Page<Contact> findByNameContainingIgnoreCase( Pageable pageable , String name );
//	Page<Contact> findByNameContainingIgnoreCaseAndStatus( Pageable pageable , String name , Status status );
}