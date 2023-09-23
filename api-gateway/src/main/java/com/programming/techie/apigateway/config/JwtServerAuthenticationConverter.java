package com.programming.techie.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtServerAuthenticationConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    JwtAuthenticationConverter jwtAuthenticationConverter =   new JwtAuthenticationConverter();


    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;
    @Value("${jwt.auth.converter.principle-attribute}")
    private String principleAttribute;
//    @Override
//    public Mono<Authentication> convert(ServerWebExchange exchange) {
//        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
//                .filter(it -> it.startsWith("Bearer "))
//                .map(it -> new BearerToken( null,it.substring(7)));
//    }

    @Override
    public Collection convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()
        ).collect(Collectors.toSet());
        return authorities;
//        return new JwtAuthenticationToken(
//                jwt,
//                authorities,
//                getPrincipleClaimName(jwt));
    }

    private String getPrincipleClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (principleAttribute != null){
            claimName = principleAttribute;
        }

        return jwt.getClaim(claimName);

    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;
        if (jwt.getClaim("resource_access") == null){
            return Set.of();
        }
        resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess.get(resourceId) == null){
            return Set.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(resourceId);

        resourceRoles = (Collection<String>) resource.get("roles");
        return resourceRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
