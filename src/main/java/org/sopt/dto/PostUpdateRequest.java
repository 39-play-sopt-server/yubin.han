package org.sopt.dto;

import org.sopt.domain.Category;

public record PostUpdateRequest(Category category, String title, String content) {
}
