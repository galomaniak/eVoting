package edu.biniek.evoting.voting;

import edu.biniek.evoting.VotingConfirmation;

public class Voter {
    private String candidate;
    private VotingConfirmation confirmation;

    public Voter(String candidate) {
        this.candidate = candidate;
    }

    public void vote(Voting voting) {
        confirmation = voting.vote((Ballot b) ->
        {
            b.check(candidate);
        });
    }

    public String getCandidate() {
        return candidate;
    }

    public VotingConfirmation getConfirmation() {
        return confirmation;
    }
}
