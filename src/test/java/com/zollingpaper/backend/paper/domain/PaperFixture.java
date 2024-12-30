package com.zollingpaper.backend.paper.domain;

import com.zollingpaper.backend.paper.dto.PaperSaveRequest;
import com.zollingpaper.backend.paper.dto.PaperSaveResponse;
import java.time.LocalDateTime;

public class PaperFixture {

    public static final LocalDateTime TOMORROW = LocalDateTime.now().plusDays(1);

    public static final PaperSaveRequest PAPER_SAVE_REQUEST_1
            = new PaperSaveRequest(
            1L,
            "Mason_",
            "hello"
    );

    public static final PaperSaveRequest PAPER_SAVE_REQUEST_2
            = new PaperSaveRequest(
            1L,
            "Liv_",
            "hello World"
    );

    public static final PaperSaveRequest PAPER_SAVE_REQUEST_3
            = new PaperSaveRequest(
            2L,
            "Todari_",
            "hello World!"
    );

    public static final PaperSaveResponse PAPER_SAVE_RESPONSE_1
            = new PaperSaveResponse(
            1L,
            1L,
            "Mason_",
            "hello"
    );

    public static final PaperSaveResponse PAPER_SAVE_RESPONSE_2
            = new PaperSaveResponse(
            2L,
            1L,
            "Liv_",
            "hello World"
    );
}
