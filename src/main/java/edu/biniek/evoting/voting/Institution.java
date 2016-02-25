package edu.biniek.evoting.voting;

import edu.biniek.evoting.voting.tables.*;

import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Institution {
    private Queue<Ballot> ballots;
    private final TableP tableP;
    private final TableQ tableQ;
    private final TableR tableR;
    private final TableS tableS;
    private final Random random = new Random();

    public Institution(Queue<Ballot> ballots) {
        this.ballots = ballots;
        tableP = new TableP(ballots);
        tableQ = new TableQ(tableP);
        tableR = new TableR(tableP);
        tableS = new TableS(ballots);
        permuteTables();
    }

    private void permuteTables() {
        tableQ.permute(random, tableR);
        tableS.permute(random, tableR);
        tableR.permute(random);
    }

    public List<TableRowP> getTableP() {
        return tableP.getRows();
    }

    public List<TableRowQ> getTableQ() {
        return tableQ.getRows();
    }

    public List<TableRowR> getTableR() {
        return tableR.getRows();
    }

    public List<TableRowS> getTableS() {
        return tableS.getRows();
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
        for (TableRowR tableRowR : tableR.getRows()) {
            tableRowR.setFlag(tableQ.get(tableRowR.getRowQ()).getConfirmationCodes().get(tableRowR.getColumnQ()).isChosen());
            tableS.get(tableRowR.getRowS()).setFlag(tableRowR.isFlag());
        }
    }

    public void clearTables(Random random) {
        for (TableRowQ tableRowQ : tableQ.getRows())
            clearTableRowQ(tableRowQ);
        for (TableRowR tableRowR : tableR.getRows())
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
