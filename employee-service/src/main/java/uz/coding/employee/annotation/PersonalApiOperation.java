package uz.coding.employee.annotation;

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
import uz.coding.employee.dto.Person;
import uz.coding.employee.model.PersonModal;
import uz.coding.employee.model.ResponseDelete;
import uz.coding.employee.service.PersonalService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations(
        {
        @RouterOperation(
                path = "/api/person/",
                produces = {MediaType.APPLICATION_JSON_VALUE},
                beanClass = PersonalService.class,
                method = RequestMethod.POST,
                beanMethod = "addPerson",
                operation = @Operation(operationId = "addPerson", responses = {
                        @ApiResponse(responseCode = "201", description = "successfully created",
                                content = @Content(schema = @Schema(implementation = Person.class))),
                        @ApiResponse(responseCode = "404", description = "Bad Request response")},
                        requestBody = @RequestBody(required = false, description = "Enter Request body as Json Object",
                                content = @Content(schema = @Schema(implementation = PersonModal.class))),
                        security = @SecurityRequirement(name = "bearerAuth")

                )),
        @RouterOperation(path = "/api/person/", produces = {
                MediaType.APPLICATION_JSON_VALUE},
                beanClass = PersonalService.class, method = RequestMethod.PUT, beanMethod = "editPerson",
                operation = @Operation(operationId = "editPerson", responses = {
                        @ApiResponse(responseCode = "200", description = "successfully updated",
                                content = @Content(schema = @Schema(implementation = Person.class))),
                        @ApiResponse(responseCode = "404", description = "Bad Request response")},
                        parameters = {@Parameter(in = ParameterIn.QUERY, name = "id",
                                schema = @Schema(type = "string", defaultValue = ""))},

                        requestBody = @RequestBody(required = false, description = "Enter Request body as Json Object",
                                content = @Content(schema = @Schema(implementation = PersonModal.class))),
                        security = @SecurityRequirement(name = "bearerAuth")

                )),
        @RouterOperation(path = "/api/person", produces = {
                MediaType.APPLICATION_JSON_VALUE},
                beanClass = PersonalService.class, method = RequestMethod.GET, beanMethod = "findAll",
                operation = @Operation(operationId = "findAll", responses = {
                        @ApiResponse(responseCode = "200", description = "successful operation",
                                content = @Content(schema = @Schema(implementation = Person.class))),
                        @ApiResponse(responseCode = "404", description = "Employees not found")},
                        parameters = {@Parameter(in = ParameterIn.PATH)},
                        security = @SecurityRequirement(name = "bearerAuth")
                )),
        @RouterOperation(path = "/api/person/", produces = {
                MediaType.APPLICATION_JSON_VALUE},
                beanClass = PersonalService.class, method = RequestMethod.GET, beanMethod = "findById",
                operation = @Operation(operationId = "findById", responses = {
                        @ApiResponse(responseCode = "200", description = "successfully found",
                                content = @Content(schema = @Schema(implementation = Person.class))),
                        @ApiResponse(responseCode = "404", description = "Bad Request response")},
                        parameters = {@Parameter(in = ParameterIn.QUERY, name = "id",
                                schema = @Schema(type = "string", defaultValue = ""))},
                        security = @SecurityRequirement(name = "bearerAuth")

                )),
        @RouterOperation(path = "/api/person/", produces = {
                MediaType.APPLICATION_JSON_VALUE},
                beanClass = PersonalService.class, method = RequestMethod.DELETE, beanMethod = "deleteById",
                operation = @Operation(operationId = "deleteById", responses = {
                        @ApiResponse(responseCode = "200", description = "successfully deleted",
                                content = @Content(schema = @Schema(implementation = ResponseDelete.class))),
                        @ApiResponse(responseCode = "404", description = "Bad Request response")},
                        parameters = {@Parameter(in = ParameterIn.QUERY, name = "id",
                        schema = @Schema(type = "string", defaultValue = ""))},
                        security = @SecurityRequirement(name = "bearerAuth")
                )),

        })

public @interface PersonalApiOperation {
}
