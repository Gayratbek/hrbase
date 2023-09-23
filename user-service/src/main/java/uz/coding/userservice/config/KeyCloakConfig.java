package uz.coding.userservice.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
public class KeyCloakConfig {
    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:8090";
    public final static String realm = "Gayrat";
    final static String clientId = "hr-rest-api";
    final static String clientSecret = "a2zeWIx87DUFam3GbyCLKbmGr493Dbdf";
//    final static String userName = "gayrat";
//    final static String password = "gayrat";

    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//                    .username(userName)
//                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .build();
    }

}
