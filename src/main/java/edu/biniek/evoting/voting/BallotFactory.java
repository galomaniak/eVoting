package edu.biniek.evoting.voting;

import java.util.*;

public class BallotFactory {
    public static final int RANDOM_CODE_LENGTH = 3;
    private String candidates[];
    private Random random;
    private int nextSerial = 0;

    public BallotFactory(String... candidates) {
        this.candidates = candidates;
        random = new Random();
    }

    public Ballot getBallot() {
        LinkedHashMap<String, String> ballotLines = new LinkedHashMap<>();
        for (String candidate : candidates)
            ballotLines.put(candidate, randomString(RANDOM_CODE_LENGTH));
        return new Ballot(ballotLines, nextSerial++);
    }

    public LinkedList<Ballot> getBallots(int number) {
        LinkedList<Ballot> ballots = new LinkedList<>();
        for (int i = 0; i < number; ++i)
            ballots.add(getBallot());
        return ballots;
    }

    private String randomString(final int length) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++)
            sb.append(randomCharacter());
        return sb.toString();
    }

    private char randomCharacter() {
        int ch;
        int number = random.nextInt(36);
        if (number < 10)
            ch = number + '0'; // digit 0-9
        else
            ch = number - 10 + 'A'; // alpha A-Z
        return (char)(ch);
    }
}
