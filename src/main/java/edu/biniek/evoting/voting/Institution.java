package edu.biniek.evoting.voting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Institution {
    private Queue<Ballot> ballots;
    private final List<TableRowP> tableP;
    private final List<TableRowQ> tableQ;
    private List<TableRowR> tableR;
    private List<TableRowS> tableS;
    private final Random random = new Random();

    public Institution(Queue<Ballot> ballots) {
        this.ballots = ballots;
        tableP = generateTableP();
        tableQ = generateTableQ();
        tableR = generateTableR();
        tableS = generateTableS();
        permuteTables();
    }


    private List<TableRowP> generateTableP() {
        List<TableRowP> rows = new LinkedList<>();
        for (Ballot ballot : ballots)
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
            for (ConfirmationCode i : rowP.getConfirmationCodes()) {
                table.add(new TableRowR(rowP.getID(), ctr, rowP.getID() * rowP.getConfirmationCodes().size() + ctr));
                ctr++;
            }
        }
        return table;
    }

    private List<TableRowS> generateTableS() {
        List<TableRowS> table = new LinkedList<>();
        for (Ballot ballot : ballots)
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
            List<ConfirmationCode> confirmationCodes = tableQ.get(row).getConfirmationCodes();
            List<Integer> permutation = IntStream.range(0, confirmationCodes.size()).boxed().collect(Collectors.toList());
            Collections.shuffle(permutation, random);
            LinkedList<ConfirmationCode> newCodes = new LinkedList<>();
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

    public Queue<Ballot> getBallots() {
        return ballots;
    }

    public void parseBallot(ObfuscatedBallot ballot) {
        TableRowQ tableRowQ = tableQ.get(ballot.getSerial());
        for (ConfirmationCode code : tableRowQ.getConfirmationCodes())
            if (code.getCode().equals(ballot.getConfirmationCode()))
                code.setChosen(true);
    }

    public void propagateVotes() {
        for (TableRowR tableRowR : tableR) {
            tableRowR.setFlag(tableQ.get(tableRowR.getRowQ()).getConfirmationCodes().get(tableRowR.getColumnQ()).isChosen());
            tableS.get(tableRowR.getRowS()).setFlag(tableRowR.isFlag());
        }
    }

    public void clearTables(Random random) {
        for (TableRowQ tableRowQ : tableQ)
            clearTableRowQ(tableRowQ);
        for (TableRowR tableRowR : tableR)
            clearTableRowR(tableRowR, random);
    }

    private void clearTableRowR(TableRowR tableRowR, Random random) {
        boolean hideLeft = random.nextBoolean();
        if (hideLeft) {
            tableRowR.setRowQ(null);
            tableRowR.setColumnQ(null);
        } else {
            tableRowR.setRowS(null);
        }
    }

    private void clearTableRowQ(TableRowQ tableRowQ) {
        List<ConfirmationCode> codes = tableRowQ.getConfirmationCodes();
        for (int i = 0; i < codes.size(); ++i)
            if (!codes.get(i).isChosen())
                codes.set(i, new ConfirmationCode("***"));
    }
}
