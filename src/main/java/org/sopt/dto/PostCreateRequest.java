package org.sopt.dto;

import org.sopt.domain.Category;

public record PostCreateRequest(Category category, String title, String content, String author) {
}
