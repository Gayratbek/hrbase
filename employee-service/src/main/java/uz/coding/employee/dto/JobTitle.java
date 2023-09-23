package uz.coding.employee.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class JobTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String description;

    @Transient
    private List<String> responsibilities;

    private String salary_range;

    private Boolean active;

    @Transient
    private List<String> goals;

    private String location;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Employee manager;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Department department;




}
