package org.sopt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessMessage;
import org.sopt.dto.CategoryResponse;
import org.sopt.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Category", description = "카테고리 API")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final PostService postService;

    public CategoryController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "카테고리 목록 조회")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getCategories() {
        SuccessMessage successMessage = SuccessMessage.CATEGORY_LIST_READ;
        return ResponseEntity.status(successMessage.getStatus())
                .body(ApiResponse.success(successMessage, postService.getCategories()));
    }
}
