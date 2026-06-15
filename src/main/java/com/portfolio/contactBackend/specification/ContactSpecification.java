package com.portfolio.contactBackend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.portfolio.contactBackend.model.Contact;
import com.portfolio.contactBackend.model.Contact.Status;

public class ContactSpecification {
    public static Specification<Contact> hasName(String name) {
        return (root, query, cb) ->
                ( name == null || name == "" ) ? null :
                cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Contact> hasStatus(Status status) {
        return (root, query, cb) ->
                ( status == null || status == Status.ALL ) ? null :
                cb.equal(root.get("status"), status);
    }
}