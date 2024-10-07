package com.twitter.user_service.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignupRequestDTO {

    private String name;
    private String username;
    private String password;
    private String bio;
    private String location;
}
