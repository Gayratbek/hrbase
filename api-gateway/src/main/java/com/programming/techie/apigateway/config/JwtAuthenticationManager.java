//package com.programming.techie.apigateway.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.ReactiveAuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
//    private final JwtSupport jwtSupport;
//    @Autowired
//    private  ReactiveUserDetailsService users;
//    @Override
//    public Mono<Authentication> authenticate(Authentication authentication) {
//
//        return Mono.justOrEmpty(authentication)
//                .filter(auth -> auth instanceof BearerToken )
//                .cast(BearerToken.class)
//                .flatMap(jwt -> validate(jwt))
//                .onErrorMap(error -> new InvalidBearerToken(error.getMessage()));
//
//    }
//    private Mono<Authentication> validate(BearerToken token){
//
//        var username = jwtSupport.getUsername(token);
//        var user = users.findByUsername(username).block();
//        if (jwtSupport.isValid(token, user )){
//            if (user !=null){
//                return Mono.just(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities()));
//            }
//        }
//        throw  new IllegalArgumentException("Token is not valid");
//
//
//    }
//}
