package com.example.e_voting_mobile.service.rest_api;

import com.example.e_voting_mobile.data.network_util.UserDetailsResponse;
import com.example.e_voting_mobile.data.network_util.VotingDataResponse;
import com.example.e_voting_mobile.util.Constants;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiHistory {
    @GET(Constants.VOTING_DATA_URL)
    Single<Response<List<VotingDataResponse>>> getVotingData(@Path("email") String email);

    @GET(Constants.USER_DETAILS_URL)
    Single<Response<UserDetailsResponse>> getUserDetails(@Path("email") String email);
}
