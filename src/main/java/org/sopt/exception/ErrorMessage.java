package org.sopt.exception;

public enum ErrorMessage {
    EMPTY_TITLE("제목을 입력해주세요."),
    EMPTY_CONTENT("내용을 입력해주세요."),
    EMPTY_AUTHOR("작성자를 입력해주세요."),
    TITLE_TOO_LONG("제목은 30자 이하로 입력해주세요."),
    POST_NOT_FOUND("존재하지 않는 게시글입니다."),
    INVALID_CATEGORY("존재하지 않는 카테고리입니다."),
    INVALID_COMMAND("잘못된 메뉴 선택입니다."),
    INVALID_NUMBER("숫자를 입력해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
