package com.sarka.auction.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final Logger logger = LoggerFactory.getLogger(JwtConverter.class);
    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                        jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                        extractRealmRoles(jwt).stream())
                .collect(Collectors.toSet());

        // Log the final authorities assigned to this token
        logger.info("Extracted Authorities: {}", authorities);

        return new JwtAuthenticationToken(jwt, authorities, jwt.getClaimAsString(JwtClaimNames.SUB));
    }

    private Collection<? extends GrantedAuthority> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if (realmAccess == null) {
            logger.info("No realm_access found in JWT");
            return Collections.emptySet();
        }

        Collection<String> roles = (Collection<String>) realmAccess.get("roles");
        if (roles == null) {
            logger.info("No roles found in realm_access");
            return Collections.emptySet();
        }

        // Log the roles being extracted
        logger.info("Roles extracted from realm_access: {}", roles);

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))  // Adds "ROLE_" prefix for Spring Security compatibility
                .collect(Collectors.toSet());
    }
}
