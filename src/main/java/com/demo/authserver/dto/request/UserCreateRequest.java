package com.demo.authserver.dto.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String username;
    private String email;
    private String password;
    private String role;
}
