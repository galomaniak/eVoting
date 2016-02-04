package edu.biniek.evoting;

public class VotingConfirmation {
    private final int serial;
    private final String code;

    public VotingConfirmation(int serial, String code) {
        this.serial = serial;
        this.code = code;
    }

    public int getSerial() {
        return serial;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return serial + "-" + code;
    }
}
