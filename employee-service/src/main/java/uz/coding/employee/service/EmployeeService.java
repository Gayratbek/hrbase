package uz.coding.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.coding.employee.dto.Department;
import uz.coding.employee.dto.Employee;
import uz.coding.employee.dto.JobTitle;
import uz.coding.employee.dto.Person;
import uz.coding.employee.mapper.EmployeeMapper;
import uz.coding.employee.model.EmployeeModel;
import uz.coding.employee.model.ResponseDelete;
import uz.coding.employee.repos.DepartmentRepository;
import uz.coding.employee.repos.EmployeeRepository;
import uz.coding.employee.repos.JobTitleRepository;
import uz.coding.employee.repos.PersonalRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PersonalRepository personalRepository;

    private final JobTitleRepository jobTitleRepository;

    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;


    public Mono<ServerResponse>  addEmployee(ServerRequest request) {

        return request.bodyToMono(EmployeeModel.class)
                .flatMap(employeeModel -> checkAndSaveEmployee(employeeModel))
                .flatMap(employee -> ServerResponse
                                        .ok()
                                        .bodyValue(employee))
                .onErrorComplete()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found from Person")));
    }


    private Mono<Employee> checkAndSaveEmployee(EmployeeModel employeeModel) {
        Optional<Person> personOptional = personalRepository.findById(employeeModel.getPersonalId());

        if (personOptional.isPresent()){
            Optional<Employee> employeeOptional = employeeRepository.findByEmpCode(employeeModel.getEmpCode());
            if (employeeOptional.isEmpty()){
                Employee employee = employeeRepository.save(employeeMapper.map(employeeModel, personOptional.get()));
                return Mono.fromCallable(()->employee);
            }
        }
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found from Person or already created"));

    }

    public Mono<ServerResponse> editEmployee(ServerRequest request) {
        Long id = Long.valueOf(request.queryParam("id").get());
        return request.bodyToMono(EmployeeModel.class)
                .flatMap(empployeeModel-> findemployeebyid(id, empployeeModel))
                .map(employeeRepository::save)
                .flatMap(employee -> ServerResponse
                        .ok()
                        .bodyValue(employee));
    }

    private Mono<Employee> findemployeebyid(Long id, EmployeeModel empployeeModel) {
        return Mono.just(employeeRepository
                .findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(employee-> {

                    Person person = getPerson(empployeeModel.getPersonalId());
                    JobTitle jobTitle = getJobTitle(empployeeModel.getJobTitleId());
                    Department department = getDepartment(empployeeModel.getDepartmentId());
                    return employeeMapper.map(employee,empployeeModel,jobTitle,department,person);
                });


    }

    private Person getPerson(Long personalId) {
        Person person;
        Optional<Person> personOptional = personalRepository
                .findById(personalId);
        if (personOptional.isPresent()) {
            person = personOptional.get();
        }
        else  {
            person =null;
        }
        return person;
    }

    @Nullable
    private JobTitle getJobTitle(Long jobTitleId) {
        JobTitle jobTitle;
        Optional<JobTitle> jobTitleOptional = jobTitleRepository
                .findById(jobTitleId);
        if (jobTitleOptional.isPresent()) {
            jobTitle = jobTitleOptional.get();
        }
        else  {
            jobTitle =null;
        }
        return jobTitle;
    }
    @Nullable
    private Department getDepartment(Long departmentId) {
        Department department;
        Optional<Department> departmentOptional = departmentRepository
                .findById(departmentId);
        if (departmentOptional.isPresent()) {
            department = departmentOptional.get();
        }
        else  {
            department =null;
        }
        return department;
    }


    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(Flux.fromIterable(employeeRepository.findAll()), Employee.class);

    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> employeeRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .doOnNext(employeeRepository::delete)
                .flatMap(p->ServerResponse.ok()
                        .bodyValue(new ResponseDelete(String.valueOf(id), "Employee","deleted", "succesfully deleted")))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));

    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> employeeRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap((employee -> ServerResponse.ok().bodyValue(employee)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));
    }
}
