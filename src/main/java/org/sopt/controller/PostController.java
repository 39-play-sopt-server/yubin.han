package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessMessage;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostPageResponse;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostUpdateRequest;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Post", description = "게시글 API")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "게시글 작성")
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createPost(@RequestBody PostCreateRequest request) {
        return toResponse(SuccessMessage.POST_CREATED, postService.createPost(request));
    }

    @Operation(summary = "게시글 목록 조회 (최신순, 페이지네이션)")
    @GetMapping
    public ResponseEntity<ApiResponse<PostPageResponse>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return toResponse(SuccessMessage.POST_LIST_READ, postService.getPosts(page, size));
    }

    @Operation(summary = "게시글 상세 조회 (조회수 1 증가)")
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long postId) {
        return toResponse(SuccessMessage.POST_READ, postService.getPost(postId));
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest request
    ) {
        postService.updatePost(postId, request);
        return toResponse(SuccessMessage.POST_UPDATED, null);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return toResponse(SuccessMessage.POST_DELETED, null);
    }

    private <T> ResponseEntity<ApiResponse<T>> toResponse(SuccessMessage successMessage, T data) {
        return ResponseEntity.status(successMessage.getStatus())
                .body(ApiResponse.success(successMessage, data));
    }
}
