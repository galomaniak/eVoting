package edu.biniek.evoting.voting;

public class ConfirmationCode {
    private final String code;
    private boolean chosen = false;

    public ConfirmationCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    @Override
    public String toString() {
        return code;
    }
}
