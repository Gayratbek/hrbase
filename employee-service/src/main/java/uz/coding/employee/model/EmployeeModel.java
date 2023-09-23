package uz.coding.employee.model;

import lombok.Data;

import java.time.Instant;

@Data
public class EmployeeModel {

    private String empCode;
    private Instant hireDate;

    private long jobTitleId;
    private long departmentId;
    private Long personalId;
}
