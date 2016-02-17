package edu.biniek.evoting.voting;

public class ObfuscatedBallot {
    private final Ballot ballot;

    public ObfuscatedBallot(Ballot ballot) {
        this.ballot = ballot;
    }

    public String getConfirmationCode() {
        return ballot.getConfirmationCode();
    }

    public int getSerial() {
        return ballot.getSerial();
    }
}
