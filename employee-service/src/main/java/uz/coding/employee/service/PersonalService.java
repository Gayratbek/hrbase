package uz.coding.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.coding.employee.dto.Person;
import uz.coding.employee.mapper.PersonMapper;
import uz.coding.employee.model.PersonModal;
import uz.coding.employee.model.ResponseDelete;
import uz.coding.employee.repos.PersonalRepository;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalService {

    //    private final TransactionalOperator transactionalOperator;
    private final PersonalRepository personalRepository;
    private final PersonMapper personMapper;

//    private final PersonalDocumentRepository personalDocumentRepository;

    public static int square(Integer n) {
        return n * n;
    }


    @Transactional
    public Mono<ServerResponse> addPerson(ServerRequest request) {

        return request.bodyToMono(PersonModal.class)
                .map(personModal -> {
                   Optional<Person> personOptional = personalRepository
                           .findByPinfl(personModal.getPinfl());
                   return personOptional.isPresent()?personOptional.get():personMapper.map(personModal);
                })
                .map(personalRepository::save)
                .flatMap(p -> ServerResponse
                        .created(URI.create("/register/" + p))
                        .bodyValue(p));
    }

    public Mono<ServerResponse> editPerson(ServerRequest request) {
        Mono<PersonModal> personModalMono = request.bodyToMono(PersonModal.class);
        Long id = Long.valueOf(request.queryParam("id").get());
        return request.bodyToMono(PersonModal.class)
                .flatMap(personModal -> {
                    Optional<Person> personOptional = personalRepository
                            .findById(id);
                    return  personOptional.isPresent()?Mono.just(personMapper.map(personOptional.get(), personModal))
                                     :Mono.empty();
                })
                .map(personalRepository::save)
                .flatMap(p -> ServerResponse
                        .ok()
                        .bodyValue(p))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));

    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok().body(Flux.fromIterable(personalRepository.findAll()), Person.class);
    }
//
    public Mono<ServerResponse> findById(ServerRequest request) {
//        Long id = Long.valueOf(request.pathVariable("id"));
        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> personalRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap((p -> ServerResponse.ok().bodyValue(p)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));


    }
//
    public Mono<ServerResponse> deleteById(ServerRequest request) {
//        Long id = Long.valueOf(request.pathVariable("id"));
        Long id = Long.valueOf(request.queryParam("id").get());

        return Mono.fromCallable(() -> personalRepository.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .doOnNext(personalRepository::delete)
                .flatMap(p->ServerResponse.ok()
                        .bodyValue(new ResponseDelete(String.valueOf(id), "Person","deleted", "succesfully deleted")))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));

//        r
    }
}
