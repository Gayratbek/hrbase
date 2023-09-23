package uz.coding.employee.controller.router;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import uz.coding.employee.annotation.DepartmentApiOperation;
import uz.coding.employee.annotation.EmployeeApiOperation;
//import uz.coding.employee.annotation.JobApiOperation;
import uz.coding.employee.annotation.JobApiOperation;
import uz.coding.employee.annotation.PersonalApiOperation;
import uz.coding.employee.service.DepartmentService;
import uz.coding.employee.service.EmployeeService;
import uz.coding.employee.service.JobtitleService;
import uz.coding.employee.service.PersonalService;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
@Tag(name = "Test APIs", description = "Test APIs for demo purpose")
public class EmployeeRouter {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final JobtitleService jobtitleService;
    private final PersonalService personalService;

    @Bean
    @DepartmentApiOperation
    RouterFunction<ServerResponse> departmentRoutes(){
        return  route(POST("/api/department/"), departmentService::addDepartment)
                .andRoute(PUT("/api/department/").and(queryParam("id", id -> true)), departmentService::editDepartment)
                .andRoute(GET("/api/department"), departmentService::findAll)
                .andRoute(GET("/api/department/").and(queryParam("id", id -> true)), departmentService::findById)
                .andRoute(DELETE("/api/department/").and(queryParam("id", id -> true)), departmentService::deleteById);
    }
    @Bean
    @JobApiOperation
    RouterFunction<ServerResponse> jobTitleRoutes(){
        return  route(POST("/api/jobTitle/"), jobtitleService::addJobTitle)
                .andRoute(PUT("/api/jobTitle/").and(queryParam("id", id -> true)), jobtitleService::editJobTitle)
                .andRoute(GET("/api/jobTitle"), jobtitleService::findAll)
                .andRoute(GET("/api/jobTitle/").and(queryParam("id", id -> true)), jobtitleService::findById)
                .andRoute(DELETE("/api/jobTitle/").and(queryParam("id", id->true)), jobtitleService::deleteById);

    }
    @Bean
    @EmployeeApiOperation

    RouterFunction<ServerResponse> employyeRoutes(){
        return  route(POST("/api/employee/"), employeeService::addEmployee)
                .andRoute(PUT("/api/employee/").and(queryParam("id", id -> true)), employeeService::editEmployee)
                .andRoute(GET("/api/employee"), employeeService::findAll)
                .andRoute(GET("/api/employee/").and(queryParam("id", id -> true)), employeeService::findById)
                .andRoute(DELETE("/api/employee/").and(queryParam("id", id -> true)), employeeService::delete);
    }
    @Bean
    @PersonalApiOperation
    RouterFunction<ServerResponse> personalInformationRoutes() {
        return route(POST("/api/person/"), personalService::addPerson)
                .andRoute(PUT("/api/person/").and(queryParam("id", id->true)), personalService::editPerson)
                .andRoute(GET("/api/person"), personalService::findAll)
                .andRoute(GET("/api/person/").and(queryParam("id", id -> true)), personalService::findById)
                .andRoute(DELETE("/api/person/").and(queryParam("id", id->true)), personalService::deleteById);
    }
}
