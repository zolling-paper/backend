package com.zollingpaper.backend.board.controller;

import com.zollingpaper.backend.board.dto.BoardDetailResponse;
import com.zollingpaper.backend.board.dto.BoardSaveRequest;
import com.zollingpaper.backend.board.dto.BoardSaveResponse;
import com.zollingpaper.backend.board.service.BoardService;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/board")
    public ResponseEntity<BoardSaveResponse> saveBoard(
            @RequestBody BoardSaveRequest request
    ) {
        BoardSaveResponse response = boardService.saveBoard(request);
        return ResponseEntity.created(URI.create("/board/" + response.id()))
                .body(response);
    }

    @GetMapping("/board/{board-id}")
    public ResponseEntity<BoardDetailResponse> getBoardDetail(
            @PathVariable(value = "board-id") Long boardId
    ) {
        BoardDetailResponse response = boardService.getBoardDetail(boardId);
        return ResponseEntity.ok(response);
    }
}
