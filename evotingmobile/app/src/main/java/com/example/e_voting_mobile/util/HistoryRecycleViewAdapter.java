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
VotingDataInfo votingDataInfo=votingDataInfoList.get(position);
        holder.vName.setText(votingDataInfo.name);
        holder.vSurname.setText(votingDataInfo.surname);
        holder.vEmail.setText(votingDataInfo.email);
        holder.vTitle.setText(votingDataInfo.name + " " + votingDataInfo.surname);
    }

    @Override
    public int getItemCount() {
        return votingDataInfoList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;
        public HistoryViewHolder(@NonNull View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}
