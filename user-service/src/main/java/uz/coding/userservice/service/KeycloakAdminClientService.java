package uz.coding.userservice.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import uz.coding.userservice.config.KeyCloakConfig;
import uz.coding.userservice.dto.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakAdminClientService  {


    private final Keycloak keycloak;
    private static final String REALM_NAME = "Gayrat";

    public List<String> searchByUsername(String username, boolean exact) {
        log.info("Searching by username: {} (exact {})", username, exact);

        UsersResource usersResource = keycloak.realm(REALM_NAME).users();
//        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        List<UserRepresentation> users = usersResource
                .searchByUsername(username, exact);

        List<String> usernames = users.stream().map(user -> user.getUsername()).collect(Collectors.toList());

        log.info("Users found by username {}", usernames);

        return usernames;


    }

//    @PostConstruct
    public void searchUsers() {
        searchByUsername("gayrat", true);
    }

    public UserRepresentation addUser(User user) {
        UsersResource usersResource = keycloak.realm(REALM_NAME).users();
        CredentialRepresentation credentialRepresentation = createPasswordCredentials(user.getPassword());

        UserRepresentation kcUser = new UserRepresentation();
        kcUser.setUsername(user.getUsername());
        kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
        kcUser.setFirstName(user.getFirstname());
        kcUser.setLastName(user.getLastname());
        kcUser.setEmail(user.getEmail());
        kcUser.setEnabled(true);
        kcUser.setEmailVerified(false);
        usersResource.create(kcUser);

        return kcUser;
    }
    private static CredentialRepresentation  createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }


}