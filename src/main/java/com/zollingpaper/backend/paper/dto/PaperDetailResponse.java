package com.zollingpaper.backend.paper.dto;

import java.time.LocalDateTime;
import com.zollingpaper.backend.paper.domain.Paper;

public record PaperDetailResponse(Long id, LocalDateTime createdAt, String name, String content) {

    public static PaperDetailResponse from(Paper paper) {
        return new PaperDetailResponse(paper.getId(),
                paper.getCreatedAt(),
                paper.getName(),
                paper.getContent());
    }
}
