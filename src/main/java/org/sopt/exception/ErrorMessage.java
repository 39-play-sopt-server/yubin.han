package org.sopt.exception;

public enum ErrorMessage {
    EMPTY_TITLE(400, "제목을 입력해주세요."),
    EMPTY_CONTENT(400, "내용을 입력해주세요."),
    EMPTY_AUTHOR(400, "작성자를 입력해주세요."),
    TITLE_TOO_LONG(400, "제목은 30자 이하로 입력해주세요."),
    INVALID_CATEGORY(400, "존재하지 않는 카테고리입니다."),
    POST_NOT_FOUND(404, "존재하지 않는 게시글입니다."),
    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다.");

    private final int status;
    private final String message;

    ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
