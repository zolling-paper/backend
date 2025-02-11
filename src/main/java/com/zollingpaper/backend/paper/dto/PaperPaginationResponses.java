package com.zollingpaper.backend.paper.dto;

import java.util.List;

public record PaperPaginationResponses(List<PaperResponse> responses, Boolean hasNext, Long prevCursor,
                                       Long nextCursor) {
}
