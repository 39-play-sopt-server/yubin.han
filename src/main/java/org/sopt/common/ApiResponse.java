package org.sopt.common;

import org.sopt.exception.ErrorMessage;

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
