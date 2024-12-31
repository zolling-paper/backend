package com.zollingpaper.backend.board.dto;

import com.zollingpaper.backend.board.domain.Board;
import java.time.LocalDateTime;

public record BoardSaveResponse(Long id, String name, LocalDateTime showDate) {

    public static BoardSaveResponse from(Board board) {
        return new BoardSaveResponse(board.getId(), board.getName(), board.getShowDate());
    }
}
