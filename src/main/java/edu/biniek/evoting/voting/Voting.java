package edu.biniek.evoting.voting;

import edu.biniek.evoting.VotingConfirmation;
import org.fest.util.VisibleForTesting;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.function.Consumer;

public class Voting {
    private Queue<Ballot> ballotsReserve;
    @VisibleForTesting
    List<Ballot> ballotsInBox;
    private final Institution institution;


    public Voting(Queue<Ballot> ballots) {
        institution = new Institution(ballots);
        this.ballotsReserve = institution.getBallots();
        ballotsInBox = new LinkedList<>();
    }

    public int countVotesFor(String candidate) {
        int ctr = 0;
        for (Ballot ballot : ballotsInBox)
            if (candidate.equals(ballot.getVote()))
                ctr++;
        return ctr;
    }

    public VotingConfirmation vote(Consumer<Ballot> consumer) {
        Ballot ballot = ballotsReserve.poll();
        consumer.accept(ballot);
        ballotsInBox.add(ballot);
        return new VotingConfirmation(ballot.getSerial(), ballot.getConfirmationCode());
    }

    public Institution getInstitution() {
        return institution;
    }

    public void finish() {
        for (Ballot ballot : ballotsInBox)
            institution.parseBallot(new ObfuscatedBallot(ballot));
        institution.propagateVotes();
        institution.clearTables(new Random());
    }
}
