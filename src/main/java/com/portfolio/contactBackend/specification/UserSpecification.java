package com.portfolio.contactBackend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.portfolio.contactBackend.model.User;
import com.portfolio.contactBackend.model.User.Role;

public class UserSpecification {
    public static Specification<User> hasName(String name) {
        return (root, query, cb) ->
                ( name == null || name == "" ) ? null :
                cb.like(root.get("username"), "%" + name + "%");
    }

	public static Specification<User> hasRole(Role role) {
		return (root, query, cb) ->
        ( role == null ) ? null :
        cb.like(root.get("role"), "%" + role + "%");
	}
}