//package uz.coding.employee.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class SecurityConfig {
////    private final JwtAuthenticationManager authenticationManager;
////    private final JwtServerAuthenticationConverter converter;
////
////    private final CustomUserDetailsService  userDetailsService;
//
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity){
//        return httpSecurity
//                .csrf().disable()
//                .build();
//    }
//
//}
