package org.sopt.dto;

import org.sopt.domain.Category;

public record CategoryResponse(int number, String name) {

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getNumber(), category.getDisplayName());
    }
}
