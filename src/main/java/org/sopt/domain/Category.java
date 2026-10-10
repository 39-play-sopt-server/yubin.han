package org.sopt.domain;

import org.sopt.exception.ErrorMessage;
import org.sopt.exception.InvalidInputException;

import java.util.Arrays;

public enum Category {
    NOTICE(1, "공지"),
    FREE(2, "자유"),
    QUESTION(3, "질문"),
    STUDY(4, "스터디");

    private final int number;
    private final String displayName;

    Category(int number, String displayName) {
        this.number = number;
        this.displayName = displayName;
    }

    public static Category from(int number) {
        return Arrays.stream(values())
                .filter(category -> category.number == number)
                .findFirst()
                .orElseThrow(() -> new InvalidInputException(ErrorMessage.INVALID_CATEGORY));
    }

    public int getNumber() {
        return number;
    }

    public String getDisplayName() {
        return displayName;
    }
}
