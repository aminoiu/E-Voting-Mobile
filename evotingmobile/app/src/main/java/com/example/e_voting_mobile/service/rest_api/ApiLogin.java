package com.example.e_voting_mobile.service.rest_api;

import com.example.e_voting_mobile.data.security.LoginRequest;
import com.example.e_voting_mobile.data.security.LoginResponse;
import com.example.e_voting_mobile.data.user.User;
import com.example.e_voting_mobile.util.Constants;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiLogin {
    @POST(Constants.LOGIN_URL)
    Single<Response<LoginResponse>> login(@Body LoginRequest loginRequest);
}
