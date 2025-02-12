package com.zollingpaper.backend.paper.controller;

import com.zollingpaper.backend.auth.util.JwtCookieConsumer;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import com.zollingpaper.backend.paper.dto.PaperDetailResponse;
import com.zollingpaper.backend.paper.dto.PaperPaginationResponses;
import com.zollingpaper.backend.paper.dto.PaperResponses;
import com.zollingpaper.backend.paper.dto.PaperSaveRequest;
import com.zollingpaper.backend.paper.dto.PaperSaveResponse;
import com.zollingpaper.backend.paper.service.PaperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "paper API")
@RestController
public class PaperController {

    private final PaperService paperService;
    private final JwtCookieConsumer jwtCookieConsumer;

    public PaperController(PaperService paperService, JwtCookieConsumer jwtCookieConsumer) {
        this.paperService = paperService;
        this.jwtCookieConsumer = jwtCookieConsumer;
    }

    @Operation(summary = "paper 생성 API", description = "특정 board에 새로운 paper를 생성합니다.")
    @PostMapping("/paper")
    public ResponseEntity<PaperSaveResponse> savePaper(
            @RequestBody PaperSaveRequest request
    ) {
        PaperSaveResponse response = paperService.savePaper(request);
        return ResponseEntity.created(URI.create("/paper/" + response.id()))
                .body(response);
    }

    @Operation(summary = "paper 조회 API", description = "특정 paper 하나를 조회합니다.")
    @GetMapping("/paper/{paper-id}")
    public ResponseEntity<PaperDetailResponse> getPaperDetail(
            @PathVariable(value = "paper-id") Long paperId,
            HttpServletRequest request
    ) {
        String authorizedBoardAddress = jwtCookieConsumer.extractSubject(request);
        PaperDetailResponse response = paperService.getPaperDetail(paperId, authorizedBoardAddress);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "paper 목록 조회 API", description = "특정 board의 paper 목록을 조회합니다.")
    @GetMapping("/board/{board-id}/papers")
    public ResponseEntity<PaperResponses> getPaperDetails(
            @PathVariable(value = "board-id") String boardId
    ) {
        PaperResponses responses = paperService.getPapers(boardId);
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "paper 목록 조회 페이지네이션 API", description = "특정 board의 paper 목록을 페이지네이션하여 조회합니다.")
    @GetMapping("/board/{board-id}/papers/paging")
    public ResponseEntity<PaperPaginationResponses> getPaginatedPaperDetails(
            @PathVariable(value = "board-id") String boardId,
            @RequestParam(value = "cursor", required = false) Long cursor,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        PaperPaginationResponses response = paperService.getPapersPagination(boardId, cursor, limit);
        return ResponseEntity.ok(response);
    }
}
