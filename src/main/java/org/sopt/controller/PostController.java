package org.sopt.controller;

import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessMessage;
import org.sopt.dto.CategoryResponse;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostSummaryResponse;
import org.sopt.dto.PostUpdateRequest;
import org.sopt.exception.BoardException;
import org.sopt.exception.ErrorMessage;
import org.sopt.service.PostService;

import java.util.List;
import java.util.function.Supplier;

/**
 * 서버의 진입점. 클라이언트의 요청을 받아 항상 ApiResponse로 응답하며, View는 알지 못한다.
 */
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public ApiResponse<Long> createPost(PostCreateRequest request) {
        return handle(() -> ApiResponse.success(SuccessMessage.POST_CREATED, postService.createPost(request)));
    }

    public ApiResponse<List<PostSummaryResponse>> getPosts() {
        return handle(() -> ApiResponse.success(SuccessMessage.POST_LIST_READ, postService.getPosts()));
    }

    public ApiResponse<PostResponse> getPost(Long id) {
        return handle(() -> ApiResponse.success(SuccessMessage.POST_READ, postService.getPost(id)));
    }

    public ApiResponse<Void> checkPostExists(Long id) {
        return handle(() -> {
            postService.validatePostExists(id);
            return ApiResponse.success(SuccessMessage.POST_EXISTS);
        });
    }

    public ApiResponse<Void> updatePost(Long id, PostUpdateRequest request) {
        return handle(() -> {
            postService.updatePost(id, request);
            return ApiResponse.success(SuccessMessage.POST_UPDATED);
        });
    }

    public ApiResponse<Void> deletePost(Long id) {
        return handle(() -> {
            postService.deletePost(id);
            return ApiResponse.success(SuccessMessage.POST_DELETED);
        });
    }

    public ApiResponse<List<CategoryResponse>> getCategories() {
        return handle(() -> ApiResponse.success(SuccessMessage.CATEGORY_LIST_READ, postService.getCategories()));
    }

    // 예외가 클라이언트까지 넘어가지 않도록 여기서 실패 응답으로 변환한다.
    private <T> ApiResponse<T> handle(Supplier<ApiResponse<T>> action) {
        try {
            return action.get();
        } catch (BoardException e) {
            return ApiResponse.fail(e.getErrorMessage());
        } catch (RuntimeException e) {
            return ApiResponse.fail(ErrorMessage.INTERNAL_SERVER_ERROR);
        }
    }
}
