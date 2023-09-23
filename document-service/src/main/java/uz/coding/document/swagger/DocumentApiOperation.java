package uz.coding.document.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.coding.document.dto.PersonDocument;
import uz.coding.document.model.PersonDocumentRequest;
import uz.coding.document.model.ResponseDelete;
import uz.coding.document.service.DocumentService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations(
        {
                @RouterOperation(path = "/api/document/", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DocumentService.class, method = RequestMethod.POST, beanMethod = "addDocument",
                        operation = @Operation(operationId = "addDocument", responses = {
                                @ApiResponse(responseCode = "201", description = "successfully created",
                                        content = @Content(schema = @Schema(implementation = PersonDocument.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                requestBody = @RequestBody(required = false, description = "Enter Request body as Json Object",
                                content = @Content(schema = @Schema(implementation = PersonDocumentRequest.class))),
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),
                @RouterOperation(path = "/api/document/", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DocumentService.class, method = RequestMethod.PUT, beanMethod = "editDocument",
                        operation = @Operation(operationId = "editDocument", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully updated",
                                        content = @Content(schema = @Schema(implementation = PersonDocument.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                parameters = {@Parameter(in = ParameterIn.PATH, name = "id", schema = @Schema(type = "string", defaultValue = ""))},
                                requestBody = @RequestBody(required = false, description = "Enter Request body as Json Object",
                                        content = @Content(schema = @Schema(implementation = PersonDocumentRequest.class))),
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),
                @RouterOperation(path = "/api/document", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DocumentService.class, method = RequestMethod.GET, beanMethod = "findAll",
                        operation = @Operation(operationId = "findAll", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully found",
                                        content = @Content(schema = @Schema(implementation = PersonDocument.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),
                @RouterOperation(path = "/api/document/", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DocumentService.class, method = RequestMethod.GET, beanMethod = "findByType",
                        operation = @Operation(operationId = "findByType", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully found",
                                        content = @Content(schema = @Schema(implementation = PersonDocument.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                parameters = @Parameter(name = "type",  in = ParameterIn.QUERY,
                                schema = @Schema(type = "string", defaultValue = "")),
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),
                @RouterOperation(path = "/api/document/", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DocumentService.class, method = RequestMethod.GET, beanMethod = "findById",
                        operation = @Operation(operationId = "findById", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully found",
                                        content = @Content(schema = @Schema(implementation = PersonDocument.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                parameters = {@Parameter(in = ParameterIn.PATH, name = "id" ,
                                        schema = @Schema(type = "string", defaultValue = ""))},
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),

                @RouterOperation(path = "/api/document/", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DocumentService.class, method = RequestMethod.DELETE, beanMethod = "delete",
                        operation = @Operation(operationId = "delete", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully deleted",
                                        content = @Content(schema = @Schema(implementation = ResponseDelete.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                parameters = {@Parameter(in = ParameterIn.QUERY, name = "id" ,
                                        schema = @Schema(type = "string", defaultValue = ""))},
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),

        })
public @interface DocumentApiOperation {
}
