package uz.coding.document.model;

import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class PersonDocumentRequest {

    private Long personId;
    private String type;
    private Instant date;
    private Map<String, Object> additionalProperties;

//    private String nomer;
//    private String issuedBy;
//    private Instant date;
//    private Instant startDate;
//    private Instant expireDate;


}
