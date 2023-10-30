package uz.coding.employee.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "personal_information")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fio;
    private String pinfl;
    private String inpc;

    private String sex;
    private String maritalStatus;
    private String address;
    private String phoneNumber;
    private String email;

    private Instant birthDate;
    private String  birthPlace;

    @OneToOne
    private Citizenship citizenship;


    @Transient
    private List<String> education;

    @Transient
    private List<String> notes;

    @Transient
    private List<String> skills;

    @Transient
    private List<String> experience;

}
