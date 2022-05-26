package com.wedeliver.serviceaccount.gateways;

import com.wedeliver.serviceaccount.domain.User;

public class UserDTO {
    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    public UserDTO(){}
    public UserDTO(String email, String username, String password, String firstname, String lastname){
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getFirstName() {
        return firstname;
    }
    public String getLastName() {
        return lastname;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public User convert2UserAccount(){
        return new User(this.email, this.username, this.password, this.firstname, this.lastname);
    }
}
