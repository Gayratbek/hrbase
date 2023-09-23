package uz.coding.employee.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class EmployeeContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String organisation;
    private Instant date;
    private String nomer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;



}
