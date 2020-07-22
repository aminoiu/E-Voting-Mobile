package com.example.e_voting_mobile.data.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VotingDataResponse {
    String votingTitle;
    String votersNumber;
    String votesNumber;
    String votingWinner;
    String candidatesNumber;
    String adminId;
    String startDate;
    String endDate;
    String status;

    public String getVotingTitle() {
        return votingTitle;
    }

    public String getVotersNumber() {
        return votersNumber;
    }

    public String getVotesNumber() {
        return votesNumber;
    }

    public String getVotingWinner() {
        return votingWinner;
    }

    public String getCandidatesNumber() {
        return candidatesNumber;
    }

    public String getAdminId() {
        return adminId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }

}
