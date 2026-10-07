package org.sopt.exception;

public class InvalidInputException extends BoardException {

    public InvalidInputException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
