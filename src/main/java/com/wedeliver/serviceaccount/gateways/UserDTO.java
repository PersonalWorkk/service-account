package com.wedeliver.serviceaccount.gateways;

import com.wedeliver.serviceaccount.domain.Role;
import com.wedeliver.serviceaccount.domain.User;

public class UserDTO {
    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Role role;

    public UserDTO(){}
    public UserDTO(String email, String username, String password, String firstname, String lastname, Role role){
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public User convert2UserAccount(){
        return new User(this.email, this.username, this.password, this.firstname, this.lastname, this.role);
    }
}
