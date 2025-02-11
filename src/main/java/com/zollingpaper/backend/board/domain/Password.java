package com.zollingpaper.backend.board.domain;

import com.zollingpaper.backend.board.exception.BoardErrorCode;
import com.zollingpaper.backend.board.exception.BoardException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Password {

    private static final String VALID_FORMAT = "^\\d{4}$";

    @Column(name = "password", nullable = false, length = 4)
    private String value;

    protected Password() {
    }

    public Password(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value == null || isInvalidFormat(value)) {
            throw new BoardException(BoardErrorCode.INVALID_PASSWORD_FORMAT);
        }
    }

    private boolean isInvalidFormat(String value) {
        return !value.matches(VALID_FORMAT);
    }

    public String getValue() {
        return value;
    }
}
