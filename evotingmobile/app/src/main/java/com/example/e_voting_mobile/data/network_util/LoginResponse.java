package com.example.e_voting_mobile.data.network_util;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private final String type = "Bearer";

    @SerializedName("token")
    private String token;
    @SerializedName("email")
    private String email;
    @SerializedName("roles")
    private List<String> roles;

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
