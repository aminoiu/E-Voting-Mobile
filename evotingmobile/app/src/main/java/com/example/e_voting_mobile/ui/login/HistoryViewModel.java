package com.example.e_voting_mobile.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_voting_mobile.data.security.UserDetailsResponse;
import com.example.e_voting_mobile.data.security.VotingDataResponse;
import com.example.e_voting_mobile.database.UserDetailsDatabase;
import com.example.e_voting_mobile.database.model.UserDetails;
import com.example.e_voting_mobile.service.voting_history.VotingHistoryService;
import com.example.e_voting_mobile.util.AppExecutors;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class HistoryViewModel extends ViewModel {
    VotingHistoryService votingHistoryService = VotingHistoryService.getInstance();
    private MutableLiveData<List<VotingDataResponse>> responseLiveData = new MutableLiveData<>();

    private UserDetailsDatabase userDetailsDatabase;

    public UserDetailsDatabase getUserDetailsDatabase() {
        return userDetailsDatabase;
    }

    public void setUserDetailsDatabase(UserDetailsDatabase userDetailsDatabase) {
        this.userDetailsDatabase = userDetailsDatabase;
    }

    public void getVotingData(String email) {
        votingHistoryService.getVotingData(email).subscribe(new SingleObserver<Response<List<VotingDataResponse>>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<List<VotingDataResponse>> value) {
                responseLiveData.setValue(value.body());
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public MutableLiveData<List<VotingDataResponse>> getResponseLiveData() {
        return responseLiveData;
    }

    public void getUserDetails(String email) {
        votingHistoryService.getUserDetails(email).subscribe(new SingleObserver<Response<UserDetailsResponse>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<UserDetailsResponse> value) {
                AppExecutors.getInstance().diskIO().execute(() -> {
                    if (value.body() != null) {
                        if (userDetailsDatabase.userDetailsDao().getUserByEmail(value.body().getEmail()).isPresent()) {
                            userDetailsDatabase.userDetailsDao().updateUser(responseToEntity(value.body()));
                        } else {
                            userDetailsDatabase.userDetailsDao().insertNewUser(responseToEntity(value.body()));
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public UserDetails responseToEntity(UserDetailsResponse response) {
        return new UserDetails(response.getName(), response.getEmail(), response.getWorkPlace(), response.getCountry(), response.getStreet(), response.getBirthDate(), response.getPhoneNumber());
    }
}
