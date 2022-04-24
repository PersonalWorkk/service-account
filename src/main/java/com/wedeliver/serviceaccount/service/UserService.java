package com.wedeliver.serviceaccount.service;

import com.wedeliver.serviceaccount.domain.User;
import com.wedeliver.serviceaccount.gateways.UserDTO;
import com.wedeliver.serviceaccount.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(){}
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(UserDTO userDTO){
        User user = userDTO.convert2UserAccount();
        userRepository.save(user);
        return user;
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}   

