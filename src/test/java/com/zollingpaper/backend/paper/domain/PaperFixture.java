package com.zollingpaper.backend.paper.domain;

import com.zollingpaper.backend.paper.dto.PaperSaveRequest;
import com.zollingpaper.backend.paper.dto.PaperSaveResponse;

public class PaperFixture {

    public static final PaperSaveRequest PAPER_SAVE_REQUEST_1
            = new PaperSaveRequest(
            "test-address",
            "Mason_",
            "hello"
    );

    public static final PaperSaveRequest PAPER_SAVE_REQUEST_2
            = new PaperSaveRequest(
            "test-address",
            "Liv_",
            "hello World"
    );

    public static final PaperSaveRequest PAPER_SAVE_REQUEST_3
            = new PaperSaveRequest(
            "test-address",
            "Todari_",
            "hello World!"
    );

    public static final PaperSaveResponse PAPER_SAVE_RESPONSE_1
            = new PaperSaveResponse(
            1L,
            "Mason_",
            "hello"
    );

    public static final PaperSaveResponse PAPER_SAVE_RESPONSE_2
            = new PaperSaveResponse(
            2L,
            "Liv_",
            "hello World"
    );
}
