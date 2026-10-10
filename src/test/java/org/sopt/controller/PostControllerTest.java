package org.sopt.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.common.ApiResponse;
import org.sopt.dto.PostCreateRequest;
import org.sopt.dto.PostResponse;
import org.sopt.repository.PostRepository;
import org.sopt.service.PostService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostControllerTest {
    private PostController postController;

    @BeforeEach
    void setUp() {
        postController = new PostController(new PostService(new PostRepository()));
    }

    @Test
    @DisplayName("게시글 작성에 성공하면 201 응답과 생성된 id를 반환한다")
    void createPost() {
        ApiResponse<Long> response = postController.createPost(new PostCreateRequest(1, "제목", "내용", "작성자"));

        assertTrue(response.success());
        assertEquals(201, response.status());
        assertEquals(1L, response.data());
    }

    @Test
    @DisplayName("제목이 비어있으면 예외 대신 400 실패 응답을 반환한다")
    void createPostWithBlankTitle() {
        ApiResponse<Long> response = postController.createPost(new PostCreateRequest(1, " ", "내용", "작성자"));

        assertFalse(response.success());
        assertEquals(400, response.status());
        assertEquals("제목을 입력해주세요.", response.message());
        assertNull(response.data());
    }

    @Test
    @DisplayName("존재하지 않는 카테고리로 작성하면 400 실패 응답을 반환한다")
    void createPostWithInvalidCategory() {
        ApiResponse<Long> response = postController.createPost(new PostCreateRequest(99, "제목", "내용", "작성자"));

        assertFalse(response.success());
        assertEquals(400, response.status());
    }

    @Test
    @DisplayName("존재하지 않는 게시글을 조회하면 404 실패 응답을 반환한다")
    void getPostNotFound() {
        ApiResponse<PostResponse> response = postController.getPost(100L);

        assertFalse(response.success());
        assertEquals(404, response.status());
        assertEquals("존재하지 않는 게시글입니다.", response.message());
    }
}
