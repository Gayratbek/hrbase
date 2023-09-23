package uz.coding.document.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.coding.document.dto.PersonDocument;
import uz.coding.document.mapper.PersonDocumentMapper;
import uz.coding.document.model.Person;
import uz.coding.document.model.PersonDocumentRequest;
import uz.coding.document.model.ResponseDelete;
import uz.coding.document.repos.PersonalDocumentRepository;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class DocumentService {


    private final PersonalDocumentRepository documentRepository;

    //
//    private final PersonalRepository personalRepository;
    private final PersonDocumentMapper documentMapper;

//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public Mono<ServerResponse> addDocument(ServerRequest request) {


        return request.bodyToMono(PersonDocumentRequest.class)
                .flatMap(personDocumentRequest -> saveDocument(personDocumentRequest))
                .flatMap(p -> ServerResponse
                        .created(URI.create("/api/" + p.getId()))
                        .bodyValue(p));
    }

    private Mono<PersonDocument> saveDocument(PersonDocumentRequest personDocumentRequest) {

        return checkPersonId(personDocumentRequest.getPersonId())
                .log()
                .flatMap(person ->{
                    PersonDocument personDocument = documentMapper.map(personDocumentRequest);
                    return documentRepository.save(personDocument);
                });

            }

    private Mono<Person> checkPersonId(Long personDocumentRequest) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://employee-service")
                .build();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/person/")
                        .queryParam("id",personDocumentRequest)
                        .build()
                )
                .retrieve().bodyToMono(Person.class);

    }

    public Mono<ServerResponse> editDocument(ServerRequest request) {

        String id = request.queryParam("id").get();

        Mono<PersonDocumentRequest> documentRequestMono = request.bodyToMono(PersonDocumentRequest.class);

        return documentRepository.findById(String.valueOf(id))
                .zipWhen(document -> documentRequestMono)
                .map(documentRequestMonoTuple-> {
                            PersonDocument document = documentRequestMonoTuple.getT1();
                            PersonDocumentRequest personDocumentRequest = documentRequestMonoTuple.getT2();
                            return documentMapper.map(document, personDocumentRequest);
                        }
                )
                .flatMap(persondocument-> {
                    return checkPersonId(persondocument.getPersonId())
                            .map(person->documentRepository.save(persondocument));
                })
                .flatMap(p -> ServerResponse
                        .ok()
                        .bodyValue(p))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {

//        Example<PersonDocument> example = Example.of(.from("Fred", "Bloggs", null));
//        Flux<Page<PersonDocument>> documents = documentRepository.findAll();

//        Pageable firstPageWithTwoElements = PageRequest.of(0, 3);


        return ServerResponse.ok().body(documentRepository.findAll(), PersonDocument.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        String id = request.queryParam("id").get();
        return documentRepository.findById(id)
                .flatMap(p -> ServerResponse
                        .ok()
                        .bodyValue(p))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")));
    }

    public Mono<ServerResponse> findByType(ServerRequest request) {
        String type = request.queryParam("type").get();

        Flux<PersonDocument> documents = documentRepository.findByType(type);
        return ServerResponse.ok().body(documents, PersonDocument.class);

    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.queryParam("id").get();
        return documentRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "topilmadi")))
                .flatMap(documentRepository::delete)
                .then(ServerResponse
                        .ok()
                        .bodyValue(new ResponseDelete(id,"PersonDocument ", "success", "document deleted" )));
    }
}
