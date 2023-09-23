package uz.coding.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.coding.employee.dto.Department;
import uz.coding.employee.dto.Employee;
import uz.coding.employee.dto.JobTitle;
import uz.coding.employee.mapper.JobTitleMapper;
import uz.coding.employee.model.JobTitleModel;
import uz.coding.employee.model.ResponseDelete;
import uz.coding.employee.repos.DepartmentRepository;
import uz.coding.employee.repos.EmployeeRepository;
import uz.coding.employee.repos.JobTitleRepository;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobtitleService {

    private final JobTitleRepository jobTitleRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final JobTitleMapper jobTitleMapper;
    public Mono<ServerResponse>  addJobTitle(ServerRequest request) {
        return request.bodyToMono(JobTitleModel.class)
                .map(jobTitleModel -> {
                    Department department = null;
                    if (jobTitleModel.getDepartmentId() != null){
                        department = getDepartment(jobTitleModel.getDepartmentId());
                    }
                    Employee employee = null;
                    if (jobTitleModel.getManagerid() != null){
                        employee = getEmployee(jobTitleModel.getManagerid());
                    }
                    return jobTitleMapper.map(jobTitleModel, department,  employee);
                }

                )
                .map(jobTitleRepository::save)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/register/" + p))
                        .bodyValue(p));
    }


    private Employee getEmployee(Long  managerId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(managerId);
        Employee employee = null;
        if (employeeOptional.isPresent()){
            employee = employeeOptional.get();
        }
        return employee;
    }

    private Department getDepartment(Long  departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        Department department = null;
        if (departmentOptional.isPresent()){
            department = departmentOptional.get();
        }
        return department;
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
            return ServerResponse.ok().body(Flux.fromIterable(jobTitleRepository.findAll()), JobTitle.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> jobTitleRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap((p -> ServerResponse.ok().bodyValue(p)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));

    }

    public Mono<ServerResponse> editJobTitle(ServerRequest request) {

        Long id = Long.valueOf(request.queryParam("id").get());
        return request.bodyToMono(JobTitleModel.class)
                .flatMap(jobTitleModel -> {
                    Optional<JobTitle> jobTitleOptional = jobTitleRepository
                            .findById(id);
                    return  jobTitleOptional.isPresent()?Mono.just(jobTitleMapper.map(jobTitleOptional.get(), jobTitleModel))
                            :Mono.empty();
                })
                .map(jobTitleRepository::save)
                .flatMap(p -> ServerResponse
                        .ok()
                        .bodyValue(p))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> jobTitleRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .doOnNext(jobTitleRepository::delete)
                .flatMap(p->ServerResponse.ok()
                        .bodyValue(new ResponseDelete(String.valueOf(id), "Job Title","deleted", "succesfully deleted")))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));

    }
}
