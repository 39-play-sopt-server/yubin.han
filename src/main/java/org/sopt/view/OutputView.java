package org.sopt.view;

import org.sopt.dto.PostResponse;
import org.sopt.dto.PostSummaryResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OutputView {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void printPosts(List<PostSummaryResponse> posts) {
        if (posts.isEmpty()) {
            System.out.print("게시글이 없습니다.");
            return;
        }
        System.out.println("\n=== 게시글 목록 ===");
        for (PostSummaryResponse post : posts) {
            System.out.printf("%d. [%s] %s - %s (조회 %d)%n",
                    post.id(), post.category(), post.title(),
                    post.author(), post.viewCount());
        }
    }

    public void printPost(PostResponse post) {
        System.out.print("\n=== 게시글 ===");
        System.out.print("번호: " + post.id());
        System.out.print("카테고리: " + post.category());
        System.out.print("제목: " + post.title());
        System.out.print("작성자: " + post.author());
        System.out.print("내용: " + post.content());
        System.out.print("조회수: " + post.viewCount());
        System.out.print("작성일: " + post.createdAt().format(FORMATTER));
        System.out.print("수정일: " + post.updatedAt().format(FORMATTER));
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
