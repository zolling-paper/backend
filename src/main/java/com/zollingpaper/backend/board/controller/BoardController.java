package com.zollingpaper.backend.board.controller;

import com.zollingpaper.backend.auth.util.JwtTokenProvider;
import com.zollingpaper.backend.auth.util.JwtTokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import com.zollingpaper.backend.board.dto.BoardDetailResponse;
import com.zollingpaper.backend.board.dto.BoardSaveRequest;
import com.zollingpaper.backend.board.dto.BoardSaveResponse;
import com.zollingpaper.backend.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "board API")
@RestController
public class BoardController {

    private final BoardService boardService;
    private final JwtTokenProvider jwtTokenProvider;

    public BoardController(BoardService boardService, JwtTokenProvider jwtTokenProvider) {
        this.boardService = boardService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "board 생성 API", description = "새로운 board를 생성합니다.")
    @PostMapping("/board")
    public ResponseEntity<BoardSaveResponse> saveBoard(
            @RequestBody BoardSaveRequest request
    ) {
        BoardSaveResponse response = boardService.saveBoard(request);
        return ResponseEntity.created(URI.create("/board/" + response.id()))
                .body(response);
    }

    @Operation(summary = "board 조회 API", description = "개별 board를 조회합니다.")
    @GetMapping("/board/{board-id}")
    public ResponseEntity<BoardDetailResponse> getBoardDetail(
            @PathVariable(value = "board-id") String accessAddress,
            HttpServletRequest request
    ) {
        JwtTokenValidator.checkToken(request, jwtTokenProvider);
        BoardDetailResponse response = boardService.getBoardDetail(accessAddress);
        return ResponseEntity.ok(response);
    }
}
