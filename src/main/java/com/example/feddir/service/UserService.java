package com.example.feddir.service;

import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class UserService {

    private final LdapTemplate ldapTemplate;

    public UserService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List<User> searchUsers(String searchTerm) {
        return ldapTemplate.search(
                query()
                        .where("objectclass").is("person")
                        .and("cn").like(searchTerm),
                new UserAttributesMapper()
        );
    }

    public User getUserByUsername(String username) {
        return ldapTemplate.search(
                query()
                        .where("uid").is(username),
                new UserAttributesMapper()
        ).stream().findFirst().orElse(null);
    }
}