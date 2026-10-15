package org.sopt.controller;

import org.sopt.common.ApiResponse;
import org.sopt.common.SuccessMessage;
import org.sopt.dto.CategoryResponse;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final PostService postService;

    public CategoryController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getCategories() {
        return ApiResponse.success(SuccessMessage.CATEGORY_LIST_READ, postService.getCategories());
    }
}
