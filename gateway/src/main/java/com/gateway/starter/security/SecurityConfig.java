package com.gateway.starter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    SecurityWebFilterChain springSecurityWebFilterchain(ServerHttpSecurity http){
        return  http.csrf(ServerHttpSecurity.CsrfSpec::disable )
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .pathMatchers("/actutor/**").permitAll()
                        .pathMatchers("/api/users/public/**").permitAll().anyExchange().authenticated())
                .oauth2ResourceServer(oauth ->oauth.jwt(Customizer.withDefaults()))
                .build();

    }
}
