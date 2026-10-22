package org.sopt.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.sopt.exception.ErrorMessage;
import org.sopt.exception.InvalidMemberException;

@Entity
public class Member extends BaseEntity {
    private static final int MAX_NAME_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = MAX_NAME_LENGTH)
    private String name;

    protected Member() {
    }

    public Member(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidMemberException(ErrorMessage.EMPTY_MEMBER_NAME);
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new InvalidMemberException(ErrorMessage.MEMBER_NAME_TOO_LONG);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
