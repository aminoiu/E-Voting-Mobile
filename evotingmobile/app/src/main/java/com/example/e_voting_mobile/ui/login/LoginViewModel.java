package com.example.e_voting_mobile.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_voting_mobile.data.network_util.LoginRequest;
import com.example.e_voting_mobile.data.network_util.LoginResponse;
import com.example.e_voting_mobile.exceptions.LoginException;
import com.example.e_voting_mobile.service.login.LoginService;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginResponse> responseLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginException> loginExceptionData = new MutableLiveData<>();

    private LoginService loginService = LoginService.getInstance();
    public void login(LoginRequest loginRequest) {
        loginService.login(loginRequest).subscribe(new SingleObserver<Response<LoginResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<LoginResponse> authenticationResponse) {
                if (authenticationResponse.code() != 404) {
                    responseLiveData.setValue(authenticationResponse.body());
                } else {
                    loginExceptionData.setValue(new LoginException("Invalid email or password", "CREDENTIALS"));
                }
            }

            @Override
            public void onError(Throwable e) {
                loginExceptionData.setValue(
                        new LoginException("Check your connection." +
                                " Contact administration if still not logging in.", "CONNECTION"));
            }
        });
    }

    public MutableLiveData<LoginResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public MutableLiveData<LoginException> getLoginExceptionData() {
        return loginExceptionData;
    }
}