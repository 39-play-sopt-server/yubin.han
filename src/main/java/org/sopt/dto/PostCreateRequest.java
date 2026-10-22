package org.sopt.dto;

public record PostCreateRequest(Long memberId, int category, String title, String content) {
}
