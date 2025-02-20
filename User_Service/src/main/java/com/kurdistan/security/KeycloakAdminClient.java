package com.kurdistan.security;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakAdminClient {

    private final Keycloak keycloak;

    public KeycloakAdminClient(
            @Value("${keycloak.server-url}") String serverUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.admin-username}") String adminUsername,
            @Value("${keycloak.admin-password}") String adminPassword,
            @Value("${keycloak.client-id}") String clientId
    ) {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .username(adminUsername)
                .password(adminPassword)
                .clientId(clientId)
                .build();
    }

    public Keycloak getInstance() {
        return keycloak;
    }
}
