package uz.coding.userservice.dto;

import lombok.Data;

@Data
public class User {
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
}
