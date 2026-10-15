package org.sopt.exception;

import org.sopt.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<ApiResponse<Void>> handleBoardException(BoardException e) {
        return toResponse(e.getErrorMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiResponse<Void>> handleInvalidRequest(Exception e) {
        return toResponse(ErrorMessage.INVALID_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) throws Exception {
        if (e instanceof ErrorResponse) {
            throw e;
        }
        return toResponse(ErrorMessage.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiResponse<Void>> toResponse(ErrorMessage errorMessage) {
        return ResponseEntity.status(errorMessage.getStatus())
                .body(ApiResponse.fail(errorMessage));
    }
}
