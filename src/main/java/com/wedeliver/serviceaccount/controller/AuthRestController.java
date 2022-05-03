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

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        User user = userService.findByUsername(username);
        if (user !=  null){
            // check if the password is the same
            if (user.getPassword().equals(userDTO.getPassword())){
                // add more user info to this token
                String token = jwtUtil.generateToken(userDTO.getUsername());
                return new ResponseEntity<String>(token, HttpStatus.OK);
            }
        }
        // return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "HTTP Status will be NOT FOUND (CODE 401)\n");
        return new ResponseEntity<String>("User credentials are not correct", HttpStatus.BAD_REQUEST); 
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        User user = userService.saveUser(userDTO);
        if (user != null){
            String token = jwtUtil.generateToken(user.getUsername());
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("There has been an error. Please try again.", HttpStatus.BAD_REQUEST); 
    }
}
