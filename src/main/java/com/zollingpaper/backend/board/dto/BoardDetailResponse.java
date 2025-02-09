package com.zollingpaper.backend.board.dto;

import java.time.LocalDateTime;
import com.zollingpaper.backend.board.domain.Board;

public record BoardDetailResponse(String id, String name, LocalDateTime showDate, boolean isPublic, int remainingDays) {

    public static BoardDetailResponse from(Board board, boolean isPublic, int remainingDays) {
        return new BoardDetailResponse(board.getAccessAddress(), board.getName(), board.getShowDate(), isPublic, remainingDays);
    }
}
