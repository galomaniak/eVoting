package edu.biniek.evoting.voting.tables;

import edu.biniek.evoting.voting.ConfirmationCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TableR {
    private List<TableRowR> rows;

    public TableR(TableP tableP) {
        rows = new LinkedList<>();
        for (TableRowP rowP : tableP.getRows()) {
            int ctr = 0;
            for (ConfirmationCode i : rowP.getConfirmationCodes()) {
                rows.add(new TableRowR(rowP.getID(), ctr, rowP.getID() * rowP.getConfirmationCodes().size() + ctr));
                ctr++;
            }
        }
    }

    public List<TableRowR> getRows() {
        return rows;
    }

    public void permute(Random random) {
        Collections.shuffle(rows, random);
    }
}
