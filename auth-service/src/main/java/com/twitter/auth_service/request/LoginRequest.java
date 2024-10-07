package com.twitter.auth_service.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
