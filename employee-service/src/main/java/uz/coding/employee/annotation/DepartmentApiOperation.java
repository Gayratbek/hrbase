package uz.coding.employee.annotation;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.*;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.coding.employee.dto.Department;
import uz.coding.employee.model.DepartmentModel;
import uz.coding.employee.model.ResponseDelete;
import uz.coding.employee.service.DepartmentService;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RouterOperations(
        {
                @RouterOperation(path = "/api/department", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DepartmentService.class, method = RequestMethod.GET, beanMethod = "findAll",
                        operation = @Operation(operationId = "findAll", responses = {
                                @ApiResponse(responseCode = "200", description = "successful operation",
                                        content = @Content(schema = @Schema(implementation = Department.class))),
                                @ApiResponse(responseCode = "404", description = "Department not found")},
                                parameters = {@Parameter(in = ParameterIn.PATH)},
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),
                @RouterOperation(
                        path = "/api/department/",
                        produces = {MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DepartmentService.class,
                        method = RequestMethod.POST,
                        beanMethod = "addDepartment",
                        operation = @Operation(operationId = "addDepartment", responses = {
                                @ApiResponse(responseCode = "201", description = "successfully created",
                                        content = @Content(schema = @Schema(implementation = Department.class))),
                                @ApiResponse(responseCode = "404", description = "Bad request response")},
                                requestBody = @RequestBody(required = true, description = "Enter request body as Json object",
                                        content = @Content(schema = @Schema(implementation = DepartmentModel.class))),
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),
                @RouterOperation(
                        path = "/api/department/",
                        produces = {MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DepartmentService.class,
                        method = RequestMethod.PUT,
                        beanMethod = "editDepartment",
                        operation = @Operation(operationId = "editDepartment", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully updated",
                                        content = @Content(schema = @Schema(implementation = Department.class))),
                                @ApiResponse(responseCode = "404", description = "Bad request response")},
                                parameters = {@Parameter(in = ParameterIn.QUERY, name = "id",
                                        schema = @Schema(type = "string", defaultValue = ""))},
                                requestBody = @RequestBody(required = true, description = "Enter request body as Json object",
                                        content = @Content(schema = @Schema(implementation = DepartmentModel.class))),
                                security = @SecurityRequirement(name = "bearerAuth")
                        )),
                @RouterOperation(path = "/api/department/", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DepartmentService.class, method = RequestMethod.GET, beanMethod = "findById",
                        operation = @Operation(operationId = "findById", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully found",
                                        content = @Content(schema = @Schema(implementation = Department.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                parameters = {@Parameter(in = ParameterIn.QUERY, name = "id",
                                        schema = @Schema(type = "string", defaultValue = ""))},
                                security = @SecurityRequirement(name = "bearerAuth")

                        )),
                @RouterOperation(path = "/api/department/", produces = {
                        MediaType.APPLICATION_JSON_VALUE},
                        beanClass = DepartmentService.class, method = RequestMethod.DELETE, beanMethod = "deleteById",
                        operation = @Operation(operationId = "deleteById", responses = {
                                @ApiResponse(responseCode = "200", description = "successfully deleted",
                                        content = @Content(schema = @Schema(implementation = ResponseDelete.class))),
                                @ApiResponse(responseCode = "404", description = "Bad Request response")},
                                parameters = {@Parameter(in = ParameterIn.QUERY, name = "id",
                                        schema = @Schema(type = "string", defaultValue = ""))},
                                security = @SecurityRequirement(name = "bearerAuth")

                        ))

        })
public @interface DepartmentApiOperation {
}
