package edu.biniek.evoting.voting;

public class TableRowR {
    private boolean flag;
    private int rowQ;
    private int columnQ;
    private int rowS;

    public TableRowR(int rowQ, int columnQ, int rowS) {
        this.flag = false;
        this.rowQ = rowQ;
        this.columnQ = columnQ;
        this.rowS = rowS;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getRowQ() {
        return rowQ;
    }

    public void setRowQ(int rowQ) {
        this.rowQ = rowQ;
    }

    public int getColumnQ() {
        return columnQ;
    }

    public void setColumnQ(int columnQ) {
        this.columnQ = columnQ;
    }

    public int getRowS() {
        return rowS;
    }

    public void setRowS(int rowS) {
        this.rowS = rowS;
    }

    @Override
    public String toString() {
        return (flag?"1":"0") + " | (" + String.format("%03d", rowQ) + ", " + columnQ + ") | " + rowS;
    }
}
