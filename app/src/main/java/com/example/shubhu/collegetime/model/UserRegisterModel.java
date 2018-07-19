package com.example.shubhu.collegetime.model;

/**
 * @author Ranosys Technologies
 */
public class UserRegisterModel {

    private String username;

    private String userPassword;

    public UserRegisterModel(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
