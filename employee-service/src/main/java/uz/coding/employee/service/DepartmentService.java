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
import uz.coding.employee.mapper.DepartmentMapper;
import uz.coding.employee.model.DepartmentModel;
import uz.coding.employee.model.ResponseDelete;
import uz.coding.employee.repos.DepartmentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    public Mono<ServerResponse>  addDepartment(ServerRequest request) {

        return request.bodyToMono(DepartmentModel.class)
                .flatMap(departmentModel -> checkAndSaveDepartment(departmentModel))
                .flatMap(department -> ServerResponse
                        .ok()
                        .bodyValue(department))
                .onErrorComplete()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found from Person")));
    }

    private Mono<Department> checkAndSaveDepartment(DepartmentModel departmentModel) {
            Optional<Department> departmentOptional = departmentRepository.findByCode(departmentModel.getCode());
            if (departmentOptional.isEmpty()){
                Department department = departmentRepository.save(departmentMapper.map(departmentModel));
                return Mono.fromCallable(()->department);
            }
        return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found from Person or already created"));

    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(Flux.fromIterable(departmentRepository.findAll()), Department.class);
    }

    public Mono<ServerResponse> editDepartment(ServerRequest request) {
        Long id = Long.valueOf(request.queryParam("id").get());
        return request.bodyToMono(DepartmentModel.class)
                .flatMap(departmentModel-> findDepartmentByid(id, departmentModel))
                .map(departmentRepository::save)
                .flatMap(department -> ServerResponse
                        .ok()
                        .bodyValue(department));
    }

    private Mono<Department> findDepartmentByid(Long id, DepartmentModel departmentModel) {
        return Mono.just(departmentRepository
                        .findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(department->  departmentMapper.map(department,departmentModel));
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> departmentRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap((department -> ServerResponse.ok().bodyValue(department)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> departmentRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .doOnNext(departmentRepository::delete)
                .flatMap(p->ServerResponse.ok()
                        .bodyValue(new ResponseDelete(String.valueOf(id), "Department","deleted", "succesfully deleted")))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));
    }
}
