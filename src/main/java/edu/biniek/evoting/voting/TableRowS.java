package edu.biniek.evoting.voting;

public class TableRowS {
    private final String name;
    private boolean flag;

    public TableRowS(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return (flag ? "1" : "0") + " | " + name;
    }
}
