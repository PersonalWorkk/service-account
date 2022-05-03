package com.wedeliver.serviceaccount.gateways;

import com.wedeliver.serviceaccount.domain.User;

public class UserDTO {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    public UserDTO(){}
    public UserDTO(String email, String username, String password, String firstName, String lastName){
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public User convert2UserAccount(){
        return new User(this.email, this.username, this.password, this.firstName, this.lastName);
    }
}