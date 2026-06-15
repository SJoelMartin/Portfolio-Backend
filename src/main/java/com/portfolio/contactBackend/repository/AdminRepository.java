package com.portfolio.contactBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.contactBackend.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {

	Admin findByUsername(String username);

}
