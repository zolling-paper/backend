package com.zollingpaper.backend.paper.dto;

import com.zollingpaper.backend.paper.domain.Paper;
import java.time.LocalDateTime;

public record PaperDetailResponse(Long paperId, LocalDateTime createdAt, String name, String content) {

    public static PaperDetailResponse from(Paper paper) {
        return new PaperDetailResponse(paper.getId(), paper.getCreatedAt(), paper.getName(), paper.getContent());
    }
}
