package uz.coding.document.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDelete {
    private String id;
    private String tablename;
    private String status;
    private String description;
}
