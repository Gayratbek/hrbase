package com.programming.techie.apigateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig   {

    private final JwtServerAuthenticationConverter jwtServerAuthenticationConverter;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange ->exchange
                        .pathMatchers(HttpMethod.GET,"/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v3/api-docs/**","/webjars/**", "/eureka/**","/document-service/**", "/employee-service/**")
                        .permitAll()
                        .pathMatchers(HttpMethod.GET,"/api/employee").hasRole("client_user")
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth-> oauth
                        .jwt().jwtAuthenticationConverter(grantedAuthoritiesExtractor()))
                .build();

    }

    Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtServerAuthenticationConverter);

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}

