package com.wedeliver.serviceaccount.controller;

import com.wedeliver.serviceaccount.domain.User;
import com.wedeliver.serviceaccount.gateways.UserDTO;
import com.wedeliver.serviceaccount.service.UserService;
import com.wedeliver.serviceaccount.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User user = userService.saveUser(userDTO);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody String userName){
        // GET request 
        // if user issue token and return token and HttpStatus.OK
        // else
        // return user does not exist
        // TODO: retrieve user from the db and then issue token if user exists
        String token = jwtUtil.generateToken(userName);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody String userName){
        // POST request add user to db
        // TODO: Create user entry in the db
        System.out.println("Info saved...");

        return new ResponseEntity<String>("Registered", HttpStatus.OK);
    }
}
