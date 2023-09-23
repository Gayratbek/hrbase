package uz.coding.userservice.dto;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
