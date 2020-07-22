package com.example.e_voting_mobile.data.voting_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotingDataInfo {
    public static final String START_DATE_PREFIX = "Start date:   ";
    public static final String STATUS_PREFIX = "Status:   ";
    public static final String VOTERS_NR_PREFIX = "Voters nr:   ";
    public static final String CANDIDATES_NR_PREFIX = "Candidates nr:   ";
    public static final String VOTES_NR_PREFIX = "Votes nr.   ";
    public static final String WINNER_PREFIX = "Winner:   ";
    public static final String INITIATED_BY_PREFIX = "Initiated by:   ";
    public String votingTitle;
    public String votersNumber;
    public String votesNumber;
    public String votingWinner;
    public String candidateNumbers;
    public String adminId;
    public String status;
    public String startDate;

    public String getVotingTitle() {
        return votingTitle;
    }

    public void setVotingTitle(String votingTitle) {
        this.votingTitle = votingTitle;
    }

    public String getVotersNumber() {
        return votersNumber;
    }

    public void setVotersNumber(String votersNumber) {
        this.votersNumber = votersNumber;
    }

    public String getVotesNumber() {
        return votesNumber;
    }

    public void setVotesNumber(String votesNumber) {
        this.votesNumber = votesNumber;
    }

    public String getVotingWinner() {
        return votingWinner;
    }

    public void setVotingWinner(String votingWinner) {
        this.votingWinner = votingWinner;
    }

    public String getCandidateNumbers() {
        return candidateNumbers;
    }

    public void setCandidateNumbers(String candidateNumbers) {
        this.candidateNumbers = candidateNumbers;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


}
