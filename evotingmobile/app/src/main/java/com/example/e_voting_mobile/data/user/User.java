package com.example.e_voting_mobile.data.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String email;
    private String password;
}
