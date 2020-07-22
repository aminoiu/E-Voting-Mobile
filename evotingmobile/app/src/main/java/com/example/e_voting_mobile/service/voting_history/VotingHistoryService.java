package com.example.e_voting_mobile.service.voting_history;

import com.example.e_voting_mobile.data.security.UserDetailsResponse;
import com.example.e_voting_mobile.data.security.VotingDataResponse;
import com.example.e_voting_mobile.service.rest_api.ApiClient;
import com.example.e_voting_mobile.service.rest_api.ApiHistory;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.http.Path;

public class VotingHistoryService {
    private static VotingHistoryService votingHistoryService;
    ApiHistory apiHistory;

    private VotingHistoryService() {
        this.apiHistory = ApiClient.getClient().create(ApiHistory.class);
    }

    public static VotingHistoryService getInstance() {
        if (votingHistoryService == null) {
            votingHistoryService = new VotingHistoryService();
        }
        return votingHistoryService;
    }

    public Single<Response<List<VotingDataResponse>>> getVotingData(@Path("email") String email) {
        return this.apiHistory.getVotingData(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Response<UserDetailsResponse>> getUserDetails(@Path("email") String email) {
        return this.apiHistory.getUserDetails(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
