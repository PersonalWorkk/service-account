package com.wedeliver.serviceaccount.service;

import com.wedeliver.serviceaccount.domain.User;
import com.wedeliver.serviceaccount.gateways.UserDTO;
import com.wedeliver.serviceaccount.repository.UserRepository;
import com.wedeliver.serviceaccount.security.HashUserPassword;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HashUserPassword hashUserPassword;

    public UserService(){}
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository
            .findByUsername(username)
            .orElseThrow(RuntimeException::new);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }
    
    @Transactional
    public User save(UserDTO userDTO){
        User user = userDTO.convert2UserAccount();
        user.setPassword(hashUserPassword.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User update(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User findUser(String username){
        return userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

}   

