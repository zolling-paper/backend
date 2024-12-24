package com.zollingpaper.backend.paper.dto;

import java.time.LocalDateTime;

public record PaperDetailResponse(Long paperId, LocalDateTime createdAt, String name, String content) {
}
