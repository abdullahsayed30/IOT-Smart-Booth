package org.ieee.iot.service.token;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/*************************************************
 * Copyright (c) 2023-2-18 Abdullah Sayed Sallam.
 ************************************************/

public class TokenAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    public static final String ROLE_AUTHORITY_PREFIX = "ROLE_";

    public static final Collection<String> AUTHORITY_ATTRIBUTE_NAMES = Arrays.asList("scope", "scp", "authorities", "roles");

    @Override
    public JwtAuthenticationToken convert(Jwt source) {
        Collection<SimpleGrantedAuthority> authorities = this.getAuthorities(source);
        return new JwtAuthenticationToken(source, authorities);
    }


    private Collection<SimpleGrantedAuthority> getAuthorities(Jwt jwt) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (String attributeName : AUTHORITY_ATTRIBUTE_NAMES) {
            Object roles = jwt.getClaims().get(attributeName);
            if (StringUtils.hasText((String) roles)) {
                authorities.addAll(
                        Arrays.stream(((String) roles).split(","))
                                .map(a -> new SimpleGrantedAuthority(ROLE_AUTHORITY_PREFIX + a))
                                .toList());
            }
        }
        return authorities;
    }

}
