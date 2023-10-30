package uz.coding.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.coding.userservice.dto.User;
import uz.coding.userservice.service.KeycloakAdminClientService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class KeycloakController {
    private final KeycloakAdminClientService kcAdminClient;

//    @Value("${db.host}")
//    private String dbHost;

    @GetMapping(value = "/find")
    public ResponseEntity<List<String>> findall(@RequestParam String username) {
        return ResponseEntity.ok(kcAdminClient.searchByUsername(username, false));
    }


    @PostMapping(value = "/create")
    public UserRepresentation createUser(@RequestBody User user) {
        return kcAdminClient.addUser(user);
    }

    @PostMapping(value = "/createrole")
    public ClientRepresentation createRole(@RequestBody String role_name) {
        return kcAdminClient.addRealmRole(role_name);
    }



}
