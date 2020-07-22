package com.example.e_voting_mobile.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting_mobile.R;
import com.example.e_voting_mobile.data.network_util.VotingDataResponse;
import com.example.e_voting_mobile.data.voting_data.VotingDataInfo;
import com.example.e_voting_mobile.database.UserDetailsDatabase;
import com.example.e_voting_mobile.service.SessionManager;
import com.example.e_voting_mobile.util.AppExecutors;
import com.example.e_voting_mobile.util.HistoryRecycleViewAdapter;
import com.example.e_voting_mobile.voting_history.HistoryActivity;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    HistoryViewModel historyViewModel;
    List<VotingDataInfo> votingDataInfos = new ArrayList<>();
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_history, container, false);
        historyViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyViewModel.setUserDetailsDatabase(UserDetailsDatabase.getInstance(getContext()));
        historyViewModel.getResponseLiveData().observe(getViewLifecycleOwner(), this::handleSuccessfulVotingDataRequest);
        historyViewModel.getVotingData(SessionManager.getInstance().getAuthenticationEmail());
        initView();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void completeWithHistoryVotingData() {
        RecyclerView recList = root.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        HistoryRecycleViewAdapter historyRecycleViewAdapter = new HistoryRecycleViewAdapter(votingDataInfos);
        recList.setAdapter(historyRecycleViewAdapter);
    }


    private void initView() {
        ((HistoryActivity) requireActivity()).updateEmailText(SessionManager.getInstance().getAuthenticationEmail());
        AppExecutors.getInstance().networkIO().execute(() -> {
            if (SessionManager.getInstance().getAuthenticatedRoles().contains("ADMIN")) {
                retrieveUserByEmail(SessionManager.getInstance().getAuthenticationEmail());
            }
        });

    }

    private void handleSuccessfulVotingDataRequest(List<VotingDataResponse> votingDataResponses) {
        if (votingDataResponses != null) {
            votingDataResponses.forEach(votingDataResponse -> {
                VotingDataInfo votingDataInfo = new VotingDataInfo();
                votingDataInfo.setVotingTitle(votingDataResponse.getVotingTitle());
                votingDataInfo.setAdminId(votingDataResponse.getAdminId());
                votingDataInfo.setCandidateNumbers(votingDataResponse.getCandidatesNumber());
                votingDataInfo.setVotersNumber(votingDataResponse.getVotersNumber());
                votingDataInfo.setVotesNumber(votingDataResponse.getVotesNumber());
                votingDataInfo.setVotingWinner(votingDataResponse.getVotingWinner());
                votingDataInfo.setStatus(votingDataResponse.getStatus());
                votingDataInfo.setStartDate(votingDataResponse.getStartDate());
                votingDataInfos.add(votingDataInfo);
            });
            completeWithHistoryVotingData();
        }
    }

    /**
     * Method which requests user details from server, when user is successful logged, and saves in local database.
     * In case this user is already presented in android local database, it will be just updated, otherwise, it will be added as a new record.
     * This is done only when the user authenticates within mobile app.
     * Later, these data from local database will be used for user details fragment accessible only for ADMINs
     *
     * @param email
     */
    private void retrieveUserByEmail(String email) {
        historyViewModel.getUserDetails(email);
    }

}
