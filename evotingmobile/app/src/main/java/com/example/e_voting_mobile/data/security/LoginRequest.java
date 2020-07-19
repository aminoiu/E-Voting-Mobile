package com.example.e_voting_mobile.data.security;


import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest(String email, String pass) {
        this.email=email;
        this.password=pass;
    }
}
