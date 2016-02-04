package edu.biniek.evoting.voting;

import java.util.LinkedHashMap;
import java.util.Map;

public class TableRowP {
    private final int id;
    private final LinkedHashMap<String, String> confirmationCodes;

    public TableRowP(int id, LinkedHashMap<String, String> confirmationCodes) {
        this.id = id;
        this.confirmationCodes = confirmationCodes;
    }

    public int getID() {
        return id;
    }

    public Map<String, String> getConfirmationCodes() {
        return confirmationCodes;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%03d", id));
        for (String code : confirmationCodes.values())
            builder.append(" |\t").append(code);
        return builder.toString();
    }
}
