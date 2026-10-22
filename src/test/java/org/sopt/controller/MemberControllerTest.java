package org.sopt.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sopt.support.ApiTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;

class MemberControllerTest extends ApiTestSupport {

    @Test
    @DisplayName("회원 생성에 성공하면 201 응답과 회원 id를 반환한다")
    void createMemberSuccess() {
        MvcTestResult result = createMember("유빈");

        assertThat(result).hasStatus(HttpStatus.CREATED);
        assertThat(result).bodyJson().extractingPath("$.data").isNotNull();
    }

    @Test
    @DisplayName("회원 이름이 비어있으면 400 실패 응답을 반환한다")
    void createMemberWithBlankName() {
        MvcTestResult result = createMember(" ");

        assertThat(result).hasStatus(HttpStatus.BAD_REQUEST);
        assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("회원 이름을 입력해주세요.");
    }

    @Test
    @DisplayName("회원을 삭제하면 회원이 작성한 게시글도 함께 삭제된다")
    void deleteMemberWithPosts() throws Exception {
        Long memberId = extractId(createMember("유빈"));
        Long postId = extractId(createPost(memberId, 1, "제목", "내용"));

        MvcTestResult deleteResult = mvc.delete().uri("/api/v1/members/{memberId}", memberId).exchange();
        MvcTestResult postResult = mvc.get().uri("/api/v1/posts/{postId}", postId).exchange();

        assertThat(deleteResult).hasStatus(HttpStatus.OK);
        assertThat(postResult).hasStatus(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("존재하지 않는 회원을 삭제하면 404 실패 응답을 반환한다")
    void deleteMemberNotFound() {
        MvcTestResult result = mvc.delete().uri("/api/v1/members/{memberId}", 100_000).exchange();

        assertThat(result).hasStatus(HttpStatus.NOT_FOUND);
        assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("존재하지 않는 회원입니다.");
    }
}
