package com.example.user.application.dto;

/**
 * Represents the request DTO for user authentication.
 * This class contains the necessary fields for a user to authenticate.
 * 
 * @param email the email of the user
 * @param password the password of the user
 */
public class AuthRequest {
    private String email;
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}