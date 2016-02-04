package edu.biniek.evoting;

import edu.biniek.evoting.voting.BallotFactory;
import edu.biniek.evoting.voting.TableRowP;
import edu.biniek.evoting.voting.Voter;
import edu.biniek.evoting.voting.Voting;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int VOTERS_NUMBER = 10;
    private static final String[] CANDIDATES = {"Anthony", "Bill"};
    private static final Random random = new Random();

    public static void main (String[] args) {
        System.out.println("Starting voting with " + VOTERS_NUMBER + " voters");
        List<Voter> voters = generateVoters();
        Voting voting = createVoting(voters);
        printVotesByVoters(voters);
        printVotesBySystem(voting);
        printTableP(voting);
    }

    private static void printTableP(Voting voting) {
        System.out.println();
        System.out.println("Table P:");
        for (TableRowP row : voting.getTableP())
            System.out.println(row);
    }

    private static void printVotesBySystem(Voting voting) {
        System.out.println();
        System.out.println("Votes by voting system:");
        for (String candidate : CANDIDATES)
            System.out.println(candidate + " : " + voting.countVotesFor(candidate));
    }

    private static void printVotesByVoters(List<Voter> voters) {
        System.out.println();
        System.out.println("Votes by voters:");
        for (Voter voter : voters)
            System.out.println(voter.getCandidate() + " : " + voter.getConfirmation());
    }

    private static Voting createVoting(List<Voter> voters) {
        BallotFactory ballotFactory = new BallotFactory(CANDIDATES);
        Voting voting = new Voting(ballotFactory.getBallots(VOTERS_NUMBER));
        for (Voter voter : voters)
            voter.vote(voting);
        return voting;
    }

    private static List<Voter> generateVoters() {
        List<Voter> voters = new LinkedList<>();
        for (int i=0; i<VOTERS_NUMBER; ++i)
            voters.add(new Voter(randomCandidate()));
        return voters;
    }

    private static String randomCandidate() {
        return CANDIDATES[random.nextInt(CANDIDATES.length)];
    }
}
