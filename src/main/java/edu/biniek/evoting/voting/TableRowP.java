package edu.biniek.evoting.voting;

import java.util.Collection;
import java.util.LinkedList;

public class TableRowP {
    private final int id;
    private final LinkedList<String> confirmationCodes;

    public TableRowP(int id, Collection<String> confirmationCodes) {
        this.id = id;
        this.confirmationCodes = new LinkedList<>(confirmationCodes);
    }

    public int getID() {
        return id;
    }

    public LinkedList<String> getConfirmationCodes() {
        return confirmationCodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%03d", id));
        for (String code : confirmationCodes)
            builder.append(" |\t").append(code);
        return builder.toString();
    }
}
