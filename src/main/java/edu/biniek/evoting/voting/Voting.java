package edu.biniek.evoting.voting;

import edu.biniek.evoting.VotingConfirmation;
import org.fest.util.VisibleForTesting;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class Voting {
    private Queue<Ballot> ballotsReserve;
    @VisibleForTesting
    List<Ballot> ballotsInBox;

    public Voting(List<Ballot> ballots) {
        this.ballotsReserve = new LinkedList<>(ballots);
        ballotsInBox = new LinkedList<>();
    }

    public VotingConfirmation vote(Consumer<Ballot> consumer) {
        Ballot ballot = ballotsReserve.poll();
        consumer.accept(ballot);
        ballotsInBox.add(ballot);
        return new VotingConfirmation(ballot.getSerial(), ballot.getConfirmationCode());
    }

    public int countVotesFor(String candidate) {
        int ctr = 0;
        for (Ballot ballot : ballotsInBox)
            if (candidate.equals(ballot.getVote()))
                ctr ++;
        return ctr;
    }

    public List<BulletinBoardRowQ> generateBulletinBoard() {
        List<BulletinBoardRowQ> rows = new LinkedList<>();
        for (Ballot ballot : ballotsInBox)
            rows.add(new BulletinBoardRowQ(ballot.getSerial(), ballot.getConfirmationCode()));
        return rows;
    }
}
