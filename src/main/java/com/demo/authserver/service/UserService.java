package com.demo.authserver.service;

import com.demo.authserver.dto.request.UserCreateRequest;
import com.demo.authserver.dto.response.UserCreateResponse;
import com.demo.authserver.entity.User;
import com.demo.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponse create(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already existed");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User savedUser = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(savedUser);

        return UserCreateResponse.builder()
                .email(savedUser.getEmail())
                .build();
    }
}
