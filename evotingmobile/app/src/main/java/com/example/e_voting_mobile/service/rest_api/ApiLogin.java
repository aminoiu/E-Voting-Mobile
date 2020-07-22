package com.example.e_voting_mobile.service.rest_api;

import com.example.e_voting_mobile.data.network_util.LoginRequest;
import com.example.e_voting_mobile.data.network_util.LoginResponse;
import com.example.e_voting_mobile.util.Constants;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiLogin {
    @POST(Constants.LOGIN_URL)
    Single<Response<LoginResponse>> login(@Body LoginRequest loginRequest);
}
