package com.zollingpaper.backend.board.dto;

import com.zollingpaper.backend.board.domain.Board;
import java.time.LocalDateTime;

public record BoardDetailResponse(Long id, String name, LocalDateTime showDate) {

    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(board.getId(), board.getName(), board.getShowDate());
    }
}
