package uz.coding.userservice.config;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.Getter;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class KeycloakProvider {

    @Value("${keycloak.auth-server-url}")
    public String serverURL;
    @Value("${keycloak.realm}")
    public String realm;
    @Value("${keycloak.resource}")
    public String clientID;
    @Value("${keycloak.credentials.secret}")
    public String clientSecret;

    private static Keycloak keycloak = null;

    public KeycloakProvider() {
    }

    public Keycloak getInstance() {
        if (keycloak == null) {

            return KeycloakBuilder.builder()
                    .realm(realm)
                    .serverUrl(serverURL)
                    .clientId(clientID)
                    .clientSecret(clientSecret)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                    .build();
        }
        return keycloak;
    }


    public KeycloakBuilder newKeycloakBuilderWithPasswordCredentials(String username, String password) {
        return KeycloakBuilder.builder() //
                .realm(realm) //
                .serverUrl(serverURL)//
                .clientId(clientID) //
                .clientSecret(clientSecret) //
                .username(username) //
                .password(password);
    }

    public JsonNode refreshToken(String refreshToken) throws UnirestException {
        String url = serverURL + "/realms/" + realm + "/protocol/openid-connect/token";
        return Unirest.post(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .field("client_id", clientID)
                .field("client_secret", clientSecret)
                .field("refresh_token", refreshToken)
                .field("grant_type", "refresh_token")
                .asJson().getBody();
    }
}
//package uz.coding.userservice.config;
//
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.keycloak.OAuth2Constants;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class KeycloakConfig {
//
//    @Autowired
//    KeycloakInitializerConfigurationProperties keycloakInitializerConfigurationProperties;
//
//    @Bean
//    protected Keycloak keycloak() {
//        return KeycloakBuilder.builder()
//                .grantType(OAuth2Constants.PASSWORD)
//                .realm(keycloakInitializerConfigurationProperties.getMasterRealm())
//                .clientId(keycloakInitializerConfigurationProperties.getClientId())
//                .username(keycloakInitializerConfigurationProperties.getUsername())
//                .password(keycloakInitializerConfigurationProperties.getPassword())
//                .serverUrl(keycloakInitializerConfigurationProperties.getUrl())
//                .build();
//    }
////    static Keycloak keycloak = null;
////    final static String serverUrl = "http://localhost:8090/";
////    public final static String realm = "http://localhost:8090/admin/master/console/#/Gayrat";
////    final static String clientId = "hr-rest-api";
////    final static String clientSecret = "a2zeWIx87DUFam3GbyCLKbmGr493Dbdf";
////    final static String userName = "admin";
////    final static String password = "123";
////
////    public KeycloakConfig() {
////    }
////
////    public static Keycloak getInstance(){
////        if (keycloak == null){
////            keycloak = KeycloakBuilder.builder()
////                    .serverUrl(serverUrl)
////                    .realm(realm)
////                    .grantType(OAuth2Constants.PASSWORD)
////                    .username(userName)
////                    .password(password)
////                    .clientId(clientId)
////                    .clientSecret(clientSecret)
////                    .resteasyClient(new ResteasyClientBuilder()
////                            .connectionPoolSize(10)
////                            .build())
////                    .build();
////        }
////        return keycloak;
////    }
//}
