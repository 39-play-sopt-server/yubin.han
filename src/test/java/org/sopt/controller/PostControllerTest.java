package org.sopt.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvcTester mvc;

    @Test
    @DisplayName("게시글 작성에 성공하면 201 응답을 반환한다")
    void createPost() {
        MvcTestResult result = createPost(1, "제목", "내용", "작성자");

        assertThat(result).hasStatus(HttpStatus.CREATED);
        assertThat(result).bodyJson().extractingPath("$.success").isEqualTo(true);
        assertThat(result).bodyJson().extractingPath("$.data").isNotNull();
    }

    @Test
    @DisplayName("제목이 비어있으면 400 실패 응답을 반환한다")
    void createPostWithBlankTitle() {
        MvcTestResult result = createPost(1, " ", "내용", "작성자");

        assertThat(result).hasStatus(HttpStatus.BAD_REQUEST);
        assertThat(result).bodyJson().extractingPath("$.success").isEqualTo(false);
        assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("제목을 입력해주세요.");
    }

    @Test
    @DisplayName("존재하지 않는 카테고리로 작성하면 400 실패 응답을 반환한다")
    void createPostWithInvalidCategory() {
        MvcTestResult result = createPost(99, "제목", "내용", "작성자");

        assertThat(result).hasStatus(HttpStatus.BAD_REQUEST);
        assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("존재하지 않는 카테고리입니다.");
    }

    @Test
    @DisplayName("존재하지 않는 게시글을 조회하면 404 실패 응답을 반환한다")
    void getPostNotFound() {
        MvcTestResult result = mvc.get().uri("/api/v1/posts/{postId}", 100_000).exchange();

        assertThat(result).hasStatus(HttpStatus.NOT_FOUND);
        assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("존재하지 않는 게시글입니다.");
    }

    @Test
    @DisplayName("게시글 id가 숫자가 아니면 400 실패 응답을 반환한다")
    void getPostWithInvalidId() {
        MvcTestResult result = mvc.get().uri("/api/v1/posts/abc").exchange();

        assertThat(result).hasStatus(HttpStatus.BAD_REQUEST);
        assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("잘못된 요청입니다.");
    }

    private MvcTestResult createPost(int category, String title, String content, String author) {
        String body = """
                {"category": %d, "title": "%s", "content": "%s", "author": "%s"}
                """.formatted(category, title, content, author);
        return mvc.post().uri("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
                .exchange();
    }
}
