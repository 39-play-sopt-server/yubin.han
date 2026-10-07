package org.sopt.domain;

import org.sopt.exception.ErrorMessage;
import org.sopt.exception.InvalidPostException;

import java.time.LocalDateTime;

public class Post {
    private static final int MAX_TITLE_LENGTH = 30;

    private final Long id;
    private final String author;
    private final LocalDateTime createdAt;
    private Category category;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime updatedAt;

    public Post(Long id, Category category, String title, String content, String author) {
        validate(title, content);
        validateAuthor(author);
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = 0;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public void update(Category category, String title, String content) {
        validate(title, content);
        this.category = category;
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void increaseViewCount() {
        viewCount++;
    }

    private void validate(String title, String content) {
        if (title == null || title.isBlank()) {
            throw new InvalidPostException(ErrorMessage.EMPTY_TITLE);
        }
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new InvalidPostException(ErrorMessage.TITLE_TOO_LONG);
        }
        if (content == null || content.isBlank()) {
            throw new InvalidPostException(ErrorMessage.EMPTY_CONTENT);
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new InvalidPostException(ErrorMessage.EMPTY_AUTHOR);
        }
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getViewCount() {
        return viewCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
