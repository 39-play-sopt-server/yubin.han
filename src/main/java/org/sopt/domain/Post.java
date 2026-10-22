package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private int viewCount;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected Post() {
    }

    public Post(Member member, Category category, String title, String content) {
        validate(title, content);
        this.member = member;
        this.category = category;
        this.title = title;
        this.content = content;
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

    public Member getMember() {
        return member;
    }

    public int getViewCount() {
        return viewCount;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
