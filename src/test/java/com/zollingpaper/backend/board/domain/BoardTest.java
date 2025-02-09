package com.zollingpaper.backend.board.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("board show date가 오늘이면 board를 공개할 수 있다.")
    @Test
    void isPublic_shouldReturnTrueWhenShowDateIsToday() {
        LocalDateTime now = LocalDateTime.now();

        Board board = new Board("test", "testName", "1234", now);

        assertTrue(board.isPublic());
    }

    @DisplayName("board show date가 오늘 이전이면 board를 공개할 수 있다.")
    @Test
    void isPublic_shouldReturnTrueWhenShowDateIsBefore() {
        LocalDateTime now = LocalDateTime.now();

        Board board = new Board("test", "testName", "1234", now.minusDays(1));

        assertTrue(board.isPublic());
    }

    @DisplayName("board show date가 오늘 이후면 board를 공개할 수 없다.")
    @Test
    void isPublic_shouldReturnFalseWhenShowDateIsAfter() {
        LocalDateTime now = LocalDateTime.now();

        Board board = new Board("test", "testName", "1234", now.plusDays(1));

        assertFalse(board.isPublic());
    }

    @DisplayName("board를 공개할 수 있으면 0을 리턴한다.")
    @Test
    void calculateRemainingDays_shouldReturn0WhenShowDateIsBefore() {
        LocalDateTime now = LocalDateTime.now();

        Board board = new Board("test", "testName", "1234", now.minusDays(1));

        assertThat(board.calculateRemainingDays()).isEqualTo(0);
    }

    @DisplayName("board를 공개할 수 없으면 공개 일자까지 남은 날을 계산한다.")
    @Test
    void calculateRemainingDays_shouldReturnRemainingDaysWhenShowDateIsBefore() {
        LocalDateTime now = LocalDateTime.now();

        Board board = new Board("test", "testName", "1234", now.plusDays(1));

        assertThat(board.calculateRemainingDays()).isEqualTo(1);
    }
}
