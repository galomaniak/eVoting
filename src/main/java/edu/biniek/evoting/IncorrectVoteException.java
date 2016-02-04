package edu.biniek.evoting;

public class IncorrectVoteException extends RuntimeException {
    public IncorrectVoteException() {
    }

    public IncorrectVoteException(String message) {
        super(message);
    }

    public IncorrectVoteException(Throwable cause) {
        super(cause);
    }
}
