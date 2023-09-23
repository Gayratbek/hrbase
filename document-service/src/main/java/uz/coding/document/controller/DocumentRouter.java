package uz.coding.document.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import uz.coding.document.service.DocumentService;
import uz.coding.document.swagger.DocumentApiOperation;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
@Tag(name = "Test APIs", description = "Test APIs for demo purpose")
public class DocumentRouter {

    private final DocumentService documentService;

    @Bean
    @DocumentApiOperation
    RouterFunction<ServerResponse> documentsRoutes() {
        return route(POST("/api/document/"), documentService::addDocument)
                .andRoute(PUT("/api/document/"), documentService::editDocument)
                .andRoute(GET("/api/document").and(accept(MediaType.APPLICATION_JSON)), documentService::findAll)
                .andRoute(GET("/api/document/").and(queryParam("id", t -> true)), documentService::findById)
                .andRoute(GET("/api/document/").and(queryParam("type", t -> true)), documentService::findByType)
                .andRoute(DELETE("/api/document/"), documentService::delete);
    }

}
