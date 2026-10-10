package org.sopt.common;

public enum SuccessMessage {
    POST_CREATED(201, "게시글이 작성되었습니다."),
    POST_LIST_READ(200, "게시글 목록 조회에 성공했습니다."),
    POST_READ(200, "게시글 조회에 성공했습니다."),
    POST_EXISTS(200, "게시글이 존재합니다."),
    POST_UPDATED(200, "게시글이 수정되었습니다."),
    POST_DELETED(200, "게시글이 삭제되었습니다."),
    CATEGORY_LIST_READ(200, "카테고리 목록 조회에 성공했습니다.");

    private final int status;
    private final String message;

    SuccessMessage(int status, String message) {
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
