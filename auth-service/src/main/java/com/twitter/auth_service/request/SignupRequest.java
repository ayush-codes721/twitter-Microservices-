package com.twitter.auth_service.request;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;
    private String username;
    private String password;
}
