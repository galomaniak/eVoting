package edu.biniek.evoting.voting;

import edu.biniek.evoting.VotingConfirmation;
import org.fest.util.VisibleForTesting;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Voting {
    private Queue<Ballot> ballotsReserve;
    @VisibleForTesting
    List<Ballot> ballotsInBox;
    private final List<TableRowP> tableP;
    private final List<TableRowQ> tableQ;
    private List<TableRowR> tableR;
    private List<TableRowS> tableS;
    private final Random random = new Random();


    public Voting(List<Ballot> ballots) {
        this.ballotsReserve = new LinkedList<>(ballots);
        ballotsInBox = new LinkedList<>();
        tableP = generateTableP();
        tableQ = generateTableQ();
        tableR = generateTableR();
        tableS = generateTableS();
        permuteTables();
    }


    private List<TableRowP> generateTableP() {
        List<TableRowP> rows = new LinkedList<>();
        for (Ballot ballot : ballotsReserve)
            rows.add(new TableRowP(ballot.getSerial(), ballot.getConfirmationCodes().values()));
        return rows;
    }

    private List<TableRowQ> generateTableQ() {
        List<TableRowQ> table = new LinkedList<>();
        for (TableRowP rowP : tableP)
            table.add(new TableRowQ(rowP));
        return table;
    }

    private List<TableRowR> generateTableR() {
        List<TableRowR> table = new LinkedList<>();
        for (TableRowP rowP : tableP) {
            int ctr = 0;
            for (String i : rowP.getConfirmationCodes()) {
                table.add(new TableRowR(rowP.getID(), ctr, rowP.getID() * rowP.getConfirmationCodes().size() + ctr));
                ctr++;
            }
        }
        return table;
    }

    private List<TableRowS> generateTableS() {
        List<TableRowS> table = new LinkedList<>();
        for (Ballot ballot : ballotsReserve)
            for (String i : ballot.getConfirmationCodes().keySet())
                table.add(new TableRowS(i));
        return table;
    }

    private void permuteTables() {
        permuteTableQ();
        permuteTableS();
        permuteTableR();
    }

    private void permuteTableQ() {
        for (int row = 0; row < tableQ.size(); ++row) {
            List<String> confirmationCodes = tableQ.get(row).getConfirmationCodes();
            List<Integer> permutation = IntStream.range(0, confirmationCodes.size()).boxed().collect(Collectors.toList());
            Collections.shuffle(permutation, random);
            LinkedList<String> newCodes = new LinkedList<>();
            for (int i = 0; i < confirmationCodes.size(); ++i)
                newCodes.add(null);
            for (int column = 0; column < confirmationCodes.size(); ++column) {
                newCodes.set(permutation.get(column), confirmationCodes.get(column));
            }
            tableQ.get(row).setConfirmationCodes(newCodes);
            for (TableRowR rowR : tableR)
                if (rowR.getRowQ() == row)
                    rowR.setColumnQ(permutation.get(rowR.getColumnQ()));
        }
    }

    private void permuteTableS() {
        List<Integer> permutation = IntStream.range(0, tableS.size()).boxed().collect(Collectors.toList());
        Collections.shuffle(permutation, random);
        LinkedList<TableRowS> newTableS = new LinkedList<>();
        for (int i = 0; i < tableS.size(); ++i)
            newTableS.add(null);
        for (int column = 0; column < newTableS.size(); ++column) {
            newTableS.set(permutation.get(column), tableS.get(column));
        }
        tableS = newTableS;
        for (TableRowR rowR : tableR) {
            rowR.setRowS(permutation.get(rowR.getRowS()));
        }
    }

    private void permuteTableR() {
        Collections.shuffle(tableR, random);
    }

    public VotingConfirmation vote(Consumer<Ballot> consumer) {
        Ballot ballot = ballotsReserve.poll();
        consumer.accept(ballot);
        ballotsInBox.add(ballot);
        return new VotingConfirmation(ballot.getSerial(), ballot.getConfirmationCode());
    }

    public int countVotesFor(String candidate) {
        int ctr = 0;
        for (Ballot ballot : ballotsInBox)
            if (candidate.equals(ballot.getVote()))
                ctr++;
        return ctr;
    }

    public List<TableRowP> getTableP() {
        return tableP;
    }

    public List<TableRowQ> getTableQ() {
        return tableQ;
    }

    public List<TableRowR> getTableR() {
        return tableR;
    }

    public List<TableRowS> getTableS() {
        return tableS;
    }
}
