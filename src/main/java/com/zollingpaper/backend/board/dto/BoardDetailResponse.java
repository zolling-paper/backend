package com.zollingpaper.backend.board.dto;

import java.time.LocalDateTime;

public record BoardDetailResponse(Long id, String name, LocalDateTime showDate) {
}
