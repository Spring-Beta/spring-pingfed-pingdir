package com.example.feddir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfig {

    @Bean
    public LdapTemplate ldapTemplate() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldaps://localhost:1636");
        contextSource.setBase("dc=example,dc=com");
        contextSource.setUserDn("cn=administrator");
        contextSource.setPassword("2FederateM0re");
        contextSource.afterPropertiesSet();

        return new LdapTemplate(contextSource);
    }
}