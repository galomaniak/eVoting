package edu.biniek.evoting.voting.tables;

import edu.biniek.evoting.voting.Ballot;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TableP {
    private List<TableRowP> rows;

    public TableP(Collection<Ballot> ballots) {
        rows = new LinkedList<>();
        for (Ballot ballot : ballots)
            rows.add(new TableRowP(ballot.getSerial(), ballot.getConfirmationCodes().values()));
    }

    public List<TableRowP> getRows() {
        return rows;
    }
}
