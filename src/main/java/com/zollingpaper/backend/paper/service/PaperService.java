package com.zollingpaper.backend.paper.service;

import com.zollingpaper.backend.board.domain.Board;
import com.zollingpaper.backend.board.repository.BoardRepository;
import com.zollingpaper.backend.global.exception.PaperException;
import com.zollingpaper.backend.paper.domain.Paper;
import com.zollingpaper.backend.paper.dto.PaperDetailResponse;
import com.zollingpaper.backend.paper.dto.PaperDetailResponses;
import com.zollingpaper.backend.paper.dto.PaperSaveRequest;
import com.zollingpaper.backend.paper.exception.PaperErrorCode;
import com.zollingpaper.backend.paper.repository.PaperRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PaperService {

    private final BoardRepository boardRepository;
    private final PaperRepository paperRepository;

    public PaperService(BoardRepository boardRepository, PaperRepository paperRepository) {
        this.boardRepository = boardRepository;
        this.paperRepository = paperRepository;
    }

    public Long savePaper(PaperSaveRequest request) {
        Board board = boardRepository.findById(request.boardId()).orElseThrow();
        Paper paper = new Paper(board, request.name(), request.content());
        Paper savedPaper = paperRepository.save(paper);

        return savedPaper.getId();
    }

    public PaperDetailResponse getPaperDetail(Long paperId) {
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new PaperException(PaperErrorCode.NOT_FOUND));

        return PaperDetailResponse.from(paper);
    }

    public PaperDetailResponses getPaperDetails(Long boardId) {
        List<Paper> papers = paperRepository.findAllByBoardId(boardId);
        List<PaperDetailResponse> responses = papers.stream()
                .map(PaperDetailResponse::from)
                .collect(Collectors.toList());

        return new PaperDetailResponses(responses);
    }
}
