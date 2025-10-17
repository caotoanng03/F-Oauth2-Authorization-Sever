package com.demo.authserver.service;

import com.demo.authserver.entity.User;
import com.demo.authserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceCustomizer implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));


//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getEmail())
//                .username(user.getUsername())
//                .roles(user.getRole())
//                .password(user.getPassword())
//                .build();
    }
}
