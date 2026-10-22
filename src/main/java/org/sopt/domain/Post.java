package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.sopt.exception.ErrorMessage;
import org.sopt.exception.InvalidPostException;

import java.time.LocalDateTime;

@Entity
public class Post extends BaseEntity {
    private static final int MAX_TITLE_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false, length = MAX_TITLE_LENGTH)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected Post() {
    }

    public Post(Category category, String title, String content, String author) {
        validate(title, content);
        validateAuthor(author);
        this.category = category;
        this.title = title;
        this.content = content;
        this.author = author;
        this.viewCount = 0;
        this.updatedAt = LocalDateTime.now();
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
