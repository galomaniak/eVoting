package edu.biniek.evoting.voting;

import java.util.LinkedList;
import java.util.List;

public class TableRowQ {
    private final int id;
    private LinkedList<String> confirmationCodes;

    public TableRowQ(int id, LinkedList<String> confirmationCodes) {
        this.id = id;
        this.confirmationCodes = confirmationCodes;
    }

    public TableRowQ(TableRowP tableRowP) {
        this.id = tableRowP.getID();
        this.confirmationCodes = tableRowP.getConfirmationCodes();
    }

    public int getId() {
        return id;
    }

    public List<String> getConfirmationCodes() {
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

    public void setConfirmationCodes(LinkedList<String> confirmationCodes) {
        this.confirmationCodes = confirmationCodes;
    }
}
