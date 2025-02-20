package com.kurdistan.service.impl;

import com.kurdistan.dto.UserDTO;
import com.kurdistan.security.KeycloakAdminClient;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakService {
    private final Keycloak keycloak;

    public KeycloakService(KeycloakAdminClient keycloakAdminClient) {
        this.keycloak = keycloakAdminClient.getInstance();
    }

    public boolean userExistsInKeycloak(String email) {
        return !keycloak.realm("E-commerce").users().search(email).isEmpty();
    }

    public String createUserInKeycloak(String email, String firstname, String lastname) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(email);
        userRepresentation.setEmail(email);
        userRepresentation.setFirstName(firstname);
        userRepresentation.setLastName(lastname);
        userRepresentation.setEnabled(true);

        Response response = keycloak.realm("E-commerce").users().create(userRepresentation);
        if (response.getStatus() != 201) {
            throw new RuntimeException("Could not create user in Keycloak");
        }

        return response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
    }


    public void setUserPassword(String userId, String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        keycloak.realm("E-commerce").users().get(userId).resetPassword(credential);
    }


    public void setUserRole(String userId, String roleName) {
        setUserClientRole(userId, "user-service", "user");
    }

    public void setUserClientRole(String userId, String clientId, String roleName) {
        // Hämta klient-ID från Keycloak
        String keycloakClientId = keycloak.realm("E-commerce")
                .clients()
                .findByClientId(clientId)
                .get(0)
                .getId();


        RoleRepresentation clientRole = keycloak.realm("E-commerce")
                .clients()
                .get(keycloakClientId)
                .roles()
                .get(roleName)
                .toRepresentation();

        keycloak.realm("E-commerce")
                .users()
                .get(userId)
                .roles()
                .clientLevel(keycloakClientId)
                .add(List.of(clientRole));
    }

}
