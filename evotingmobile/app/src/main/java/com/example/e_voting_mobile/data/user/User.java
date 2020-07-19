package com.example.e_voting_mobile.data.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class User {
    @SerializedName("email")
    private String email;
    @SerializedName("roles")
    private List<String> roles;

    public String getEmail() {
        return email;
    }
}
