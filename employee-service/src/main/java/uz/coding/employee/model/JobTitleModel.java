package uz.coding.employee.model;

import lombok.Data;

import java.util.List;

@Data
public class JobTitleModel {

    private String code;
    private String name;
    private String description;
    private List<String> responsibilities;
    private String salary_range;
    private Boolean active;
    private List<String> goals;
    private String location;
    private Long managerid;
    private Long departmentId;
}
