package edu.biniek.evoting.voting.tables;

import edu.biniek.evoting.voting.ConfirmationCode;

import java.util.Collection;
import java.util.LinkedList;

public class TableRowP {
    private final int id;
    private final LinkedList<ConfirmationCode> confirmationCodes;

    public TableRowP(int id, Collection<String> confirmationCodes) {
        this.id = id;
        this.confirmationCodes = new LinkedList<>();
        for (String code : confirmationCodes)
            this.confirmationCodes.add(new ConfirmationCode(code));
    }

    public int getID() {
        return id;
    }

    public LinkedList<ConfirmationCode> getConfirmationCodes() {
        return confirmationCodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%03d", id));
        for (ConfirmationCode code : confirmationCodes)
            builder.append(" |\t").append(code);
        return builder.toString();
    }
}
