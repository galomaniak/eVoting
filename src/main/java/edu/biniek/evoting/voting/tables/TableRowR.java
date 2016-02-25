package edu.biniek.evoting.voting.tables;

public class TableRowR {
    private boolean flag;
    private Integer rowQ;
    private Integer columnQ;
    private Integer rowS;

    public TableRowR(Integer rowQ, Integer columnQ, Integer rowS) {
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

    public Integer getRowQ() {
        return rowQ;
    }

    public void setRowQ(Integer rowQ) {
        this.rowQ = rowQ;
    }

    public Integer getColumnQ() {
        return columnQ;
    }

    public void setColumnQ(Integer columnQ) {
        this.columnQ = columnQ;
    }

    public Integer getRowS() {
        return rowS;
    }

    public void setRowS(Integer rowS) {
        this.rowS = rowS;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(flag?"1":"0").append(" | ");
        if (rowQ != null && columnQ != null)
            buffer.append("(").append(String.format("%03d", rowQ)).append(", ").append(columnQ).append(")");
        else
            buffer.append("********");
        buffer.append(" | ");
        if (rowS != null)
            buffer.append(rowS);
        else
            buffer.append("***");
        return buffer.toString();
    }
}
