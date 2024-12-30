package com.zollingpaper.backend.paper.controller;

import com.zollingpaper.backend.paper.dto.PaperDetailResponse;
import com.zollingpaper.backend.paper.dto.PaperDetailResponses;
import com.zollingpaper.backend.paper.dto.PaperSaveRequest;
import com.zollingpaper.backend.paper.dto.PaperSaveResponse;
import com.zollingpaper.backend.paper.service.PaperService;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaperController {

    private final PaperService paperService;

    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @PostMapping("/paper")
    public ResponseEntity<PaperSaveResponse> savePaper (
            @RequestBody PaperSaveRequest request
    ) {
        PaperSaveResponse response = paperService.savePaper(request);
        return ResponseEntity.created(URI.create("/paper/" + response.id()))
                .body(response);
    }

    @GetMapping("/paper/{paper-id}")
    public ResponseEntity<PaperDetailResponse> getPaperDetail(
            @PathVariable(value = "paper-id") Long paperId
    ) {
        PaperDetailResponse response = paperService.getPaperDetail(paperId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/board/{board-id}/papers")
    public ResponseEntity<PaperDetailResponses> getPaperDetails(
            @PathVariable(value = "board-id") Long boardId
    ) {
        PaperDetailResponses responses = paperService.getPaperDetails(boardId);
        return ResponseEntity.ok(responses);
    }
}
