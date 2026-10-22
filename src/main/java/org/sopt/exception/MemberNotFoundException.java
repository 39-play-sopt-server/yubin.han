package org.sopt.exception;

public class MemberNotFoundException extends BoardException {

    public MemberNotFoundException() {
        super(ErrorMessage.MEMBER_NOT_FOUND);
    }
}
