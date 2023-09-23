package uz.coding.employee.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private String empCode;

    @OneToOne(cascade = CascadeType.ALL)
    private JobTitle jobTitle;

    @OneToOne(cascade = CascadeType.ALL)
    private Department department;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Person person;

    private Instant hireDate;

    private Long salary;

}
