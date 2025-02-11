package com.zollingpaper.backend.paper.dto;

import java.time.LocalDateTime;
import com.zollingpaper.backend.paper.domain.Paper;

public record PaperResponse(Long id, LocalDateTime createdAt, String name) {

    public static PaperResponse from(Paper paper) {
        return new PaperResponse(paper.getId(), paper.getCreatedAt(), paper.getName());
    }
}
