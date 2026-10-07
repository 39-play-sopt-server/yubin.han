package org.sopt.dto;

import org.sopt.domain.Post;

public record PostSummaryResponse(Long id, String category, String title, String author, int viewCount) {

    public static PostSummaryResponse from(Post post) {
        return new PostSummaryResponse(
                post.getId(),
                post.getCategory().getDisplayName(),
                post.getTitle(),
                post.getAuthor(),
                post.getViewCount()
        );
    }
}
