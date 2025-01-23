package com.kurdistan.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String role;
    private String password;
    private String imgUrl;
}
