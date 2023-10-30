package uz.coding.employee.dto;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "citizenship")
@RequiredArgsConstructor
public class Citizenship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String fullname;
    private String countryName;
    private String code;
    private String code2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
