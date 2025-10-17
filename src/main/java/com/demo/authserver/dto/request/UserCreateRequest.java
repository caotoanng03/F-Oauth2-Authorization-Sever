package com.demo.authserver.dto.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    private String email;
    private String password;
    private String role;
}
