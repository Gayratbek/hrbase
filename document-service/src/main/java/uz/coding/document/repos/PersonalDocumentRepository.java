package uz.coding.document.repos;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import uz.coding.document.dto.PersonDocument;

@Repository
public interface PersonalDocumentRepository extends ReactiveMongoRepository<PersonDocument, String> {
    Flux<PersonDocument> findByType(String type);


//    default Flux<PersonDocument> applyPagination(Flux<PersonDocument> documents, Pageable pageable) {
//        return documents.buffer(pageable.getPageSize(), (pageable.getPageNumber() + 1))
//                .elementAt(pageable.getPageNumber(), new ArrayList<>())
//                .flatMapMany(Flux::fromIterable);
//    }



//    @Override
//    Flux<PersonDocument> findAll(Pageable pageable);
}
