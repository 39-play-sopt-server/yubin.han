package org.sopt.exception;

public class PostNotFoundException extends BoardException {

    public PostNotFoundException() {
        super(ErrorMessage.POST_NOT_FOUND);
    }
}
