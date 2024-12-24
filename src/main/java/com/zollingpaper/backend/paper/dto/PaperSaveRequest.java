package com.zollingpaper.backend.paper.dto;

public record PaperSaveRequest(Long boardId, String name, String content) {
}
