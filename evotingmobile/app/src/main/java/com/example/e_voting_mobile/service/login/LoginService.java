package com.example.e_voting_mobile.service.login;

import com.example.e_voting_mobile.data.security.LoginRequest;
import com.example.e_voting_mobile.data.security.LoginResponse;
import com.example.e_voting_mobile.service.rest_api.ApiClient;
import com.example.e_voting_mobile.service.rest_api.ApiLogin;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.Body;

public class LoginService {
    private ApiLogin apiLogin;
    private static LoginService loginService;

    private LoginService() {
    this.apiLogin= ApiClient.getClient().create(ApiLogin.class);
    }

    public static LoginService getInstance(){
        if(loginService==null){
            loginService=new LoginService();
        }
        return loginService;
    }

    public Single<Response<LoginResponse>> login(@Body LoginRequest loginRequest){
        return this.apiLogin.login(loginRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
