package com.zollingpaper.backend.board.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.zollingpaper.backend.board.exception.BoardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PasswordTest {

    @DisplayName("password가 4자리가 아니거나, 숫자가 아닌 문자가 존재하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"123", "12345", "123a", "a234", "abcd"})
    void validate_shouldThrowExceptionWhenPasswordIsInvalid(String value) {
        assertThatThrownBy(() -> new Password(value))
                .isInstanceOf(BoardException.class);
    }
}
