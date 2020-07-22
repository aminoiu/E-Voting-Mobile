package com.example.e_voting_mobile.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_voting_mobile.R;
import com.example.e_voting_mobile.data.voting_data.VotingDataInfo;

import java.util.List;

public class HistoryRecycleViewAdapter extends RecyclerView.Adapter<HistoryRecycleViewAdapter.HistoryViewHolder> {
    private List<VotingDataInfo> votingDataInfoList;

    public HistoryRecycleViewAdapter(List<VotingDataInfo> votingDataInfoList) {
        this.votingDataInfoList = votingDataInfoList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.history_card_view, parent, false);

        return new HistoryViewHolder(itemView);
    }

   @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
       VotingDataInfo votingDataInfo = votingDataInfoList.get(position);
       holder.vVotingTitle.setText(votingDataInfo.getVotingTitle());
       holder.vStartDateValue.setText(VotingDataInfo.START_DATE_PREFIX.concat(votingDataInfo.getStartDate()));
       holder.vStatusValue.setText(VotingDataInfo.STATUS_PREFIX.concat(votingDataInfo.getStatus()));
       holder.vVoterNumberValue.setText(VotingDataInfo.VOTERS_NR_PREFIX.concat(votingDataInfo.getVotersNumber()));
       holder.vCandidateNumberValue.setText(VotingDataInfo.CANDIDATES_NR_PREFIX.concat(votingDataInfo.getCandidateNumbers()));
       holder.vVotesNumberValue.setText(VotingDataInfo.VOTES_NR_PREFIX.concat(votingDataInfo.getVotesNumber()));
       holder.vWinnerValue.setText(VotingDataInfo.WINNER_PREFIX.concat(votingDataInfo.getVotingWinner()));
       holder.vInitiatorValue.setText(VotingDataInfo.INITIATED_BY_PREFIX.concat(votingDataInfo.getAdminId()));

   }

    @Override
    public int getItemCount() {
        return votingDataInfoList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        protected TextView vVotingTitle;
        protected TextView vStartDateValue;
        protected TextView vStatusValue;
        protected TextView vVoterNumberValue;
        protected TextView vCandidateNumberValue;
        protected TextView vVotesNumberValue;
        protected TextView vWinnerValue;
        protected TextView vInitiatorValue;

        public HistoryViewHolder(@NonNull View v) {
            super(v);
            vVotingTitle = v.findViewById(R.id.votingTitle);
            vStartDateValue = v.findViewById(R.id.startDateValue);
            vStatusValue = v.findViewById(R.id.statusValue);
            vVoterNumberValue = v.findViewById(R.id.voterNumberValue);
            vCandidateNumberValue = v.findViewById(R.id.candidateNumberValue);
            vVotesNumberValue = v.findViewById(R.id.votesNumberValue);
            vWinnerValue = v.findViewById(R.id.winnerValue);
            vInitiatorValue = v.findViewById(R.id.initiatorValue);
        }
    }
}
