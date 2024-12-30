package com.zollingpaper.backend.board.domain;

import com.zollingpaper.backend.board.dto.BoardSaveRequest;
import com.zollingpaper.backend.board.dto.BoardSaveResponse;
import java.time.LocalDateTime;

public class BoardFixture {

    public static final LocalDateTime TOMORROW = LocalDateTime.now().plusDays(1);

    public static final BoardSaveRequest BOARD_SAVE_REQUEST_1
            = new BoardSaveRequest(
            "Mason",
            "1234",
            TOMORROW
    );

    public static final BoardSaveRequest BOARD_SAVE_REQUEST_2
            = new BoardSaveRequest(
            "Liv",
            "1234",
            TOMORROW
    );

    public static final BoardSaveResponse BOARD_SAVE_RESPONSE_1
            = new BoardSaveResponse(
            1L,
            "Mason",
            TOMORROW
    );

    public static final BoardSaveResponse BOARD_SAVE_RESPONSE_2
            = new BoardSaveResponse(
            2L,
            "Liv",
            TOMORROW
    );
}
