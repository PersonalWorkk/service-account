package com.wedeliver.serviceaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wedeliver.serviceaccount.domain.Jwt;
import com.wedeliver.serviceaccount.gateways.LoginDTO;
import com.wedeliver.serviceaccount.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
    
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Jwt login(@RequestBody LoginDTO loginDTO){
        return loginService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }
}
