package uz.coding.userservice.controller;

import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.coding.userservice.config.KeycloakProvider;
import uz.coding.userservice.dto.User;
import uz.coding.userservice.service.KeycloakAdminClientService;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
public class KeycloakController {
//    private final KeycloakAdminClientService kcAdminClient;

    @Autowired
    KeycloakAdminClientService kcAdminClient;


    private final KeycloakProvider kcProvider;

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(KeycloakController.class);


    public KeycloakController(KeycloakAdminClientService kcAdminClient, KeycloakProvider kcProvider) {
        this.kcProvider = kcProvider;
        this.kcAdminClient = kcAdminClient;
    }


    @GetMapping(value = "/find")
    public ResponseEntity<List<String>> findall() {
        return ResponseEntity.ok(kcAdminClient.searchByUsername("gayrat", false));
    }


    @PostMapping(value = "/create")
    public UserRepresentation createUser(@RequestBody User user) {
        return kcAdminClient.addUser(user);
    }

//    @PostMapping(value = "/create")
//    public ResponseEntity<Response> createUser(@RequestBody CreateUserRequest user) {
//        Response createdResponse = kcAdminClient.createKeycloakUser(user);
//        return ResponseEntity.ok(createdResponse);
//
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AccessTokenResponse> login(@NotNull @RequestBody LoginRequest loginRequest) {
//        Keycloak keycloak = kcProvider.newKeycloakBuilderWithPasswordCredentials(loginRequest.getUsername(), loginRequest.getPassword()).build();
//
//        AccessTokenResponse accessTokenResponse = null;
//        try {
//            accessTokenResponse = keycloak.tokenManager().getAccessToken();
//            return ResponseEntity.status(HttpStatus.OK).body(accessTokenResponse);
//        } catch (BadRequestException ex) {
//            LOG.warn("invalid account. User probably hasn't verified email.", ex);
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(accessTokenResponse);
//        }
//
//    }


}
