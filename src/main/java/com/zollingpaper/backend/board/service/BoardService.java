package com.zollingpaper.backend.board.service;

import com.zollingpaper.backend.board.domain.Board;
import com.zollingpaper.backend.board.dto.BoardDetailResponse;
import com.zollingpaper.backend.board.dto.BoardSaveRequest;
import com.zollingpaper.backend.board.dto.BoardSaveResponse;
import com.zollingpaper.backend.board.exception.BoardErrorCode;
import com.zollingpaper.backend.board.exception.BoardException;
import com.zollingpaper.backend.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final AccessAddressGenerator accessAddressGenerator;

    public BoardService(BoardRepository boardRepository, AccessAddressGenerator accessAddressGenerator) {
        this.boardRepository = boardRepository;
        this.accessAddressGenerator = accessAddressGenerator;
    }

    public BoardSaveResponse saveBoard(BoardSaveRequest request) {
        String accessAddress = accessAddressGenerator.generate();
        Board board = new Board(accessAddress, request.name(), request.password(), request.showDate());
        Board savedBoard = boardRepository.save(board);

        return BoardSaveResponse.from(savedBoard);
    }

    public BoardDetailResponse getBoardDetail(String accessAddress) {
        Board board = getBoardByAccessAddress(accessAddress);

        return BoardDetailResponse.from(board, board.isPublic(), board.calculateRemainingDays());
    }

    public Board getBoardByAccessAddress(String accessAddress) {
        return boardRepository.findByAccessAddress(accessAddress).orElseThrow(() -> new BoardException(BoardErrorCode.NOT_FOUND));
    }

    public Board getBoard(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new BoardException(BoardErrorCode.NOT_FOUND));
    }
}
