package edu.biniek.evoting.voting;

public class BulletinBoardRowQ {
    private final int id;
    private final String confirmationCode;

    public BulletinBoardRowQ(int id, String confirmationCode) {
        this.id = id;
        this.confirmationCode = confirmationCode;
    }

    public int getID() {
        return id;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    @Override
    public String toString() {
        return id + " : " + confirmationCode;
    }
}
