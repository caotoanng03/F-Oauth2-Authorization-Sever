package com.demo.authserver.controller;

import com.demo.authserver.dto.request.UserCreateRequest;
import com.demo.authserver.dto.response.UserCreateResponse;
import com.demo.authserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserCreateResponse create(@RequestBody UserCreateRequest request) {
        return this.userService.create(request);
    }
}
