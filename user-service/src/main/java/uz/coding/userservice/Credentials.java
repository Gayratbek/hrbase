package uz.coding.userservice;

import org.keycloak.representations.idm.CredentialRepresentation;

public class Credentials {

    public static CredentialRepresentation credentialRepresentation(String password){
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

}
