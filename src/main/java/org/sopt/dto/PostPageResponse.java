package org.sopt.dto;

import org.sopt.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public record PostPageResponse(
        List<PostSummaryResponse> posts,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
) {
    public static PostPageResponse from(Page<Post> postPage) {
        return new PostPageResponse(
                postPage.getContent().stream()
                        .map(PostSummaryResponse::from)
                        .toList(),
                postPage.getNumber(),
                postPage.getSize(),
                postPage.getTotalElements(),
                postPage.getTotalPages(),
                postPage.hasNext()
        );
    }
}
