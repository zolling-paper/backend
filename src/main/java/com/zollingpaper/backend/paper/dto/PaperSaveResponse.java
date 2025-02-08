package com.zollingpaper.backend.paper.dto;

import com.zollingpaper.backend.paper.domain.Paper;

public record PaperSaveResponse(Long id, String name, String content) {

    public static PaperSaveResponse from(Paper paper) {
        return new PaperSaveResponse(paper.getId(), paper.getName(), paper.getContent());
    }
}
