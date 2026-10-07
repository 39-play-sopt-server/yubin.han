package org.sopt.exception;


public class BoardException extends RuntimeException {

    public BoardException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
