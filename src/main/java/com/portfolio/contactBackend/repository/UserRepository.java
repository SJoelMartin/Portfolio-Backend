package com.portfolio.contactBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.portfolio.contactBackend.model.User;

public interface UserRepository extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {

	User findByUsername(String username);

	void deleteByUsername(String username);

}
