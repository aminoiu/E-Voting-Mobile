package com.example.e_voting_mobile.data.security;

import com.example.e_voting_mobile.data.user.User;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class LoginResponse {
    private final String type = "Bearer";

    @SerializedName("token")
    private String token;
    @SerializedName("email")
    private String email; //TODO: make sure it works

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }
}
