package com.zollingpaper.backend.board.service;

import com.zollingpaper.backend.board.domain.Board;
import com.zollingpaper.backend.board.dto.BoardDetailResponse;
import com.zollingpaper.backend.board.dto.BoardSaveRequest;
import com.zollingpaper.backend.board.dto.BoardSaveResponse;
import com.zollingpaper.backend.board.exception.BoardErrorCode;
import com.zollingpaper.backend.board.repository.BoardRepository;
import com.zollingpaper.backend.global.exception.BoardException;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardSaveResponse saveBoard(BoardSaveRequest request) {
        Board board = new Board(request.name(), request.password(), request.showDate());
        Board savedBoard = boardRepository.save(board);

        return BoardSaveResponse.from(savedBoard);
    }

    public BoardDetailResponse getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardException(BoardErrorCode.NOT_FOUND));

        return BoardDetailResponse.from(board);
    }
}
