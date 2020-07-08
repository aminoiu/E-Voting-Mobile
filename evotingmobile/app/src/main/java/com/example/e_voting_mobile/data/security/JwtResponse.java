package com.example.e_voting_mobile.data.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private String username;
}
