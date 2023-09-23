package com.programming.techie.apigateway.config;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class BearerToken extends AbstractAuthenticationToken {
    private String value;

    public BearerToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public BearerToken(Collection<? extends GrantedAuthority> authorities, String value) {
        super(authorities);
        this.value = value;
    }

    @Override
    public Object getCredentials() {
        return value;
    }

    @Override
    public Object getPrincipal() {
        return value;
    }
}
