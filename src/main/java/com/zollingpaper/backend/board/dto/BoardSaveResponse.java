package com.zollingpaper.backend.board.dto;

import java.time.LocalDateTime;
import com.zollingpaper.backend.board.domain.Board;

public record BoardSaveResponse(String id, String name, LocalDateTime showDate) {

    public static BoardSaveResponse from(Board board) {
        return new BoardSaveResponse(board.getAccessAddress(), board.getName(), board.getShowDate());
    }
}
