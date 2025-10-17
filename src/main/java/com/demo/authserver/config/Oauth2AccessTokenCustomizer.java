package com.demo.authserver.config;

import com.demo.authserver.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class Oauth2AccessTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {

//        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
//            context.getClaims().claims(claims -> {
//                Object principal = context.getPrincipal().getPrincipal();
//                User user = (User) principal;
//
//                Set<String> roles = AuthorityUtils.authorityListToSet(user.getAuthorities()).stream().map(c -> c.replaceFirst("^ROLE_", "")).collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
//                claims.put("roles", roles);
//                claims.put("email", user.getUsername());
//            });
//        }

        Object principalObj = context.getPrincipal().getPrincipal();

        if (principalObj instanceof User user) {
            // necessary?
//            Set<String> roles = AuthorityUtils.authorityListToSet(user.getAuthorities()).stream()
//                    .map(c -> c.replaceFirst("^ROLE_", ""))
//                    .collect(Collectors.toSet());

            // Common claims for both tokens
            context.getClaims().claim("email", user.getEmail());
            context.getClaims().claim("role", user.getRole());

            // Extra logic per token type (optional)
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                context.getClaims().claim("token_type", "access_token");
            } else if ("id_token".equals(context.getTokenType().getValue())) {
                context.getClaims().claim("token_type", "id_token");
                context.getClaims().claim("username", user.getUsername()); // example custom claim
            }
        }
    }
}