package org.sopt.exception;

public class BoardException extends RuntimeException {
    private final ErrorMessage errorMessage;

    public BoardException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
