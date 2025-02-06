package com.kurdistan.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private List<AddressDTO> addresses;
    private String role;
    private String password;
    private String imgUrl;
}
