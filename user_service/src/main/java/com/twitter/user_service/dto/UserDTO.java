package com.twitter.user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String bio;
    private String location;
    private LocalDateTime createdAt;
}
