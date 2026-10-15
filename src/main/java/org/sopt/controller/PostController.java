package org.sopt.controller;

import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessMessage;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostResponse;
import org.sopt.dto.PostSummaryResponse;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createPost(@RequestBody PostCreateRequest request) {
        return toResponse(SuccessMessage.POST_CREATED, postService.createPost(request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostSummaryResponse>>> getPosts() {
        return toResponse(SuccessMessage.POST_LIST_READ, postService.getPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable Long postId) {
        return toResponse(SuccessMessage.POST_READ, postService.getPost(postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest request
    ) {
        postService.updatePost(postId, request);
        return toResponse(SuccessMessage.POST_UPDATED, null);
    }

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
