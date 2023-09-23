package uz.coding.employee.model;

import lombok.Data;

@Data
public class DepartmentModel {

    private String name;
    private String code;
    private String description;
    private String location;

    private Long departmentId;
    private Long managerId;


}
