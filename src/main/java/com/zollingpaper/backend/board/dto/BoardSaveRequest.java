package com.zollingpaper.backend.board.dto;

import java.time.LocalDateTime;

public record BoardSaveRequest(String name, String password, LocalDateTime showDate) {
}
