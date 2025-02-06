package com.zollingpaper.backend.paper.dto;

import java.util.List;

public record PaperDetailPaginationResponse(List<PaperDetailResponse> responses, Boolean hasNext, Long prevCursor, Long nextCursor) {
}
