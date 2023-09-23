//package com.programming.techie.apigateway.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//    private String username;
//    private String password;
//    private String email;
//    private Instant created;
//    private boolean enabled;
//
//    private String roles;
//    private String permissions;
//
//    public List<String> getRoleList(){
//        if(this.roles.length() > 0){
//            return Arrays.asList(this.roles.split(","));
//        }
//        return new ArrayList<>();
//    }
//
//    public List<String> getPermissionList(){
//        if(this.permissions.length() > 0){
//            return Arrays.asList(this.permissions.split(","));
//        }
//        return new ArrayList<>();
//    }
//
//}
