package org.sopt.dto;

public record PostCreateRequest(int category, String title, String content, String author) {
}
