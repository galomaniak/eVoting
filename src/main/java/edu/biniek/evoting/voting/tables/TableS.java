package edu.biniek.evoting.voting.tables;

import edu.biniek.evoting.voting.Ballot;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableS {
    private List<TableRowS> rows;

    public TableS(Collection<Ballot> ballots) {
        rows = new LinkedList<>();
        for (Ballot ballot : ballots)
            for (String i : ballot.getConfirmationCodes().keySet())
                rows.add(new TableRowS(i));
    }

    public int size() {
        return rows.size();
    }

    public TableRowS get(int index) {
        return rows.get(index);
    }

    public void permute(Random random, TableR tableR) {
        List<Integer> permutation = IntStream.range(0, rows.size()).boxed().collect(Collectors.toList());
        Collections.shuffle(permutation, random);
        LinkedList<TableRowS> newTableS = new LinkedList<>();
        for (int i = 0; i < rows.size(); ++i)
            newTableS.add(null);
        for (int column = 0; column < newTableS.size(); ++column) {
            newTableS.set(permutation.get(column), rows.get(column));
        }
        rows = newTableS;
        for (TableRowR rowR : tableR.getRows()) {
            rowR.setRowS(permutation.get(rowR.getRowS()));
        }
    }

    public List<TableRowS> getRows() {
        return rows;
    }
}
