package org.sopt.dto;

import org.sopt.domain.Post;

import java.time.LocalDateTime;


public record PostResponse(
        Long id,
        String category,
        String title,
        String content,
        String author,
        int viewCount,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getCategory().getDisplayName(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getViewCount(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
