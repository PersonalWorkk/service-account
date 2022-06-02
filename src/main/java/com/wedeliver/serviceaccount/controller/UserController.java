package com.wedeliver.serviceaccount.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wedeliver.serviceaccount.domain.Role;
import com.wedeliver.serviceaccount.domain.User;
import com.wedeliver.serviceaccount.gateways.UserDTO;
import com.wedeliver.serviceaccount.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @PutMapping("/users")
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("/users/role")
    public Role getUserRoleFromToken(Authentication token){
        JwtAuthenticationToken tokenObj = (JwtAuthenticationToken) token;
        Map<String, Object> attributes = tokenObj.getTokenAttributes();
        String username = (String) attributes.get("username");
        return userService.findUser(username).getRole();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
