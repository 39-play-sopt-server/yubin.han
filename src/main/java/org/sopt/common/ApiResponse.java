package org.sopt.common;

import org.sopt.exception.ErrorMessage;

/**
 * 서버가 클라이언트에게 돌려주는 공통 응답 형식.
 * 성공/실패와 관계없이 항상 같은 구조로 응답한다.
 */
public record ApiResponse<T>(boolean success, int status, String message, T data) {

    public static <T> ApiResponse<T> success(SuccessMessage successMessage, T data) {
        return new ApiResponse<>(true, successMessage.getStatus(), successMessage.getMessage(), data);
    }

    public static ApiResponse<Void> success(SuccessMessage successMessage) {
        return success(successMessage, null);
    }

    public static <T> ApiResponse<T> fail(ErrorMessage errorMessage) {
        return new ApiResponse<>(false, errorMessage.getStatus(), errorMessage.getMessage(), null);
    }
}
