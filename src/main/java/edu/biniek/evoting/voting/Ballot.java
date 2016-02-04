package edu.biniek.evoting.voting;

import edu.biniek.evoting.IncorrectVoteException;

import java.util.LinkedHashMap;

public class Ballot {
    private final LinkedHashMap<String, String> confirmationCodes;
    private String chosenName;
    private final int serial;

    public Ballot(LinkedHashMap<String, String> confirmationCodes, int serial) {
        this.confirmationCodes = confirmationCodes;
        this.serial = serial;
    }

    public String check(String name) {
        if (!confirmationCodes.containsKey(name))
            throw new IncorrectVoteException();
        chosenName = name;
        return confirmationCodes.get(name);
    }

    public String getVote() {
        return chosenName;
    }

    public String getConfirmationCode() {
        return confirmationCodes.get(chosenName);
    }

    public int getSerial() {
        return serial;
    }

    public LinkedHashMap<String, String> getConfirmationCodes() {
        return confirmationCodes;
    }
}
