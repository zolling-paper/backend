package com.zollingpaper.backend.board.dto;

import java.time.LocalDateTime;
import com.zollingpaper.backend.board.domain.Board;

public record BoardDetailResponse(String id, String name, LocalDateTime showDate) {

    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(board.getAccessAddress(), board.getName(), board.getShowDate());
    }
}
