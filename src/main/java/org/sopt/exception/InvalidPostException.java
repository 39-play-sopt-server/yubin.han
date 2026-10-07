package org.sopt.exception;

public class InvalidPostException extends BoardException {

    public InvalidPostException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
