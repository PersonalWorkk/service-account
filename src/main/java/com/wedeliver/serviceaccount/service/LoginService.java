package com.wedeliver.serviceaccount.service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.wedeliver.serviceaccount.config.JwtCreator;
import com.wedeliver.serviceaccount.domain.Jwt;
import com.wedeliver.serviceaccount.exception.IncorrectCredentialsException;
import com.wedeliver.serviceaccount.security.HashUserPassword;

@Service
public class LoginService {
    @Autowired
    private JwtCreator jwtCreator;
    @Autowired
    private UserService userService;
    @Autowired
    private HashUserPassword hashUserPassword;


    public Jwt login(String username, String password){
        UserDetails userDetails = userService.loadUserByUsername(username);

        if(!hashUserPassword.decode(password, userDetails.getPassword())){
            throw new IncorrectCredentialsException();
        }

        Map<String, String> claims = new HashMap<>();
        claims.put("username", username);
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        
        claims.put("authorities", authorities);
        claims.put("userId", String.valueOf(1));

        return jwtCreator.createJwtForClaims(username, claims);
    }
    
}
