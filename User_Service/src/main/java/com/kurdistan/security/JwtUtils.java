package com.kurdistan.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private final JwtDecoder jwtDecoder;

    public JwtUtils(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    // Metod för att hämta användar-ID från token
    public String getUserIdFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getClaimAsString("sub"); // "sub" är standard för användar-ID i Keycloak
    }

    // Metod för att hämta e-post från token
    public String getEmailFromToken(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getClaimAsString("email"); // "email" är standard för e-post i Keycloak
    }
}
