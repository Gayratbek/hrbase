package uz.coding.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.coding.document.model.Person;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
@Document(collection = "document")
@Builder
public class PersonDocument {
    @Id
    private String id;
    private Instant date;
    private String type;
    private Long personId;


    private Map<String, Object> additionalProperties;

//    private String serie;
//    private String nomer;
//    private String issuedBy;
//    private Instant documentDate;
//    private Instant startDate;
//    private Instant expireDate;
}