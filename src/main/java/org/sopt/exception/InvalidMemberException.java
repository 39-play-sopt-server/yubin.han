package org.sopt.exception;

public class InvalidMemberException extends BoardException {

    public InvalidMemberException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
