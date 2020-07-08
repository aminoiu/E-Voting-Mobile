package com.example.e_voting_mobile.service.rest_api;

import com.example.e_voting_mobile.data.security.JwtResponse;
import com.example.e_voting_mobile.data.user.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface ApiService {
    @POST("auth/sign-in")
    Call<JwtResponse> login(@Body User user);
}
