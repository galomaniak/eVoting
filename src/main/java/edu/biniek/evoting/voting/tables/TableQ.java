package edu.biniek.evoting.voting.tables;

import edu.biniek.evoting.voting.ConfirmationCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableQ {
    private final List<TableRowQ> rows;

    public TableQ(TableP tableP) {
        rows = new LinkedList<>();
        for (TableRowP rowP : tableP.getRows())
            rows.add(new TableRowQ(rowP));
    }

    public TableRowQ get(int index) {
        return rows.get(index);
    }

    public List<TableRowQ> getRows() {
        return rows;
    }

    public void permute(Random random, TableR tableR) {
        for (int row = 0; row < rows.size(); ++row) {
            List<ConfirmationCode> confirmationCodes = rows.get(row).getConfirmationCodes();
            List<Integer> permutation = IntStream.range(0, confirmationCodes.size()).boxed().collect(Collectors.toList());
            Collections.shuffle(permutation, random);
            LinkedList<ConfirmationCode> newCodes = new LinkedList<>();
            for (int i = 0; i < confirmationCodes.size(); ++i)
                newCodes.add(null);
            for (int column = 0; column < confirmationCodes.size(); ++column) {
                newCodes.set(permutation.get(column), confirmationCodes.get(column));
            }
            rows.get(row).setConfirmationCodes(newCodes);
            for (TableRowR rowR : tableR.getRows())
                if (rowR.getRowQ() == row)
                    rowR.setColumnQ(permutation.get(rowR.getColumnQ()));
        }
    }
}
