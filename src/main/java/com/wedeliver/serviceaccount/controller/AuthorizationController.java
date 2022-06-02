package com.wedeliver.serviceaccount.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import com.wedeliver.serviceaccount.domain.Role;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    
    @PostMapping("/authorize")
    @ResponseStatus(HttpStatus.OK)
    public Role authorize(Authentication jwtToken){
        JwtAuthenticationToken token = (JwtAuthenticationToken) jwtToken;
        Map<String, Object> attributes = token.getTokenAttributes();

        return Role.valueOf(((String) attributes.get("authorities")).split("_")[1]);
    }
}
