package com.example.feddir.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class OAuth2ClientConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(pingClientRegistration());
    }

    private ClientRegistration pingClientRegistration() {
        return ClientRegistration.withRegistrationId("ping")
                .clientId("spring-app") // Your client ID
                .clientSecret("hgPFFcaqm8GN4eNA1nrffoSoA798N1jLQpkAx20NqFj0cEiKwQ74v1mAiBCIDbyC") // Your client secret
                .scope("openid", "profile")
                .authorizationUri("https://localhost:9031/as/authorization.oauth2")
                .tokenUri("https://localhost:9031/as/token.oauth2")
                .userInfoUri("https://localhost:9031/idp/userinfo.openid")
                .redirectUri("http://localhost:8080/login/oauth2/code/pingfederate")
                .authorizationGrantType(org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE)
                .build();
    }
}
