package org.sopt.support;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class ApiTestSupport {

    @Autowired
    protected MockMvcTester mvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void cleanUp() {
        postRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    protected MvcTestResult createMember(String name) {
        return mvc.post().uri("/api/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name": "%s"}
                        """.formatted(name))
                .exchange();
    }

    protected MvcTestResult createPost(Long memberId, int category, String title, String content) {
        return mvc.post().uri("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"memberId": %d, "category": %d, "title": "%s", "content": "%s"}
                        """.formatted(memberId, category, title, content))
                .exchange();
    }

    protected Long extractId(MvcTestResult result) throws UnsupportedEncodingException {
        Number id = JsonPath.read(result.getResponse().getContentAsString(StandardCharsets.UTF_8), "$.data");
        return id.longValue();
    }
}
