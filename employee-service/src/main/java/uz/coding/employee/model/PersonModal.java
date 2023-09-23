package uz.coding.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonModal {
    private String fio;
    private String pinfl;
    private String documentId;

}
