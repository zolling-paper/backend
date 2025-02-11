package com.zollingpaper.backend.paper.service;

import java.util.List;
import com.zollingpaper.backend.board.domain.Board;
import com.zollingpaper.backend.board.service.BoardService;
import com.zollingpaper.backend.paper.domain.Paper;
import com.zollingpaper.backend.paper.dto.PaperDetailPaginationResponse;
import com.zollingpaper.backend.paper.dto.PaperDetailResponse;
import com.zollingpaper.backend.paper.dto.PaperDetailResponses;
import com.zollingpaper.backend.paper.dto.PaperSaveRequest;
import com.zollingpaper.backend.paper.dto.PaperSaveResponse;
import com.zollingpaper.backend.paper.exception.PaperErrorCode;
import com.zollingpaper.backend.paper.exception.PaperException;
import com.zollingpaper.backend.paper.repository.PaperRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaperService {

    private final BoardService boardService;
    private final PaperRepository paperRepository;

    public PaperService(BoardService boardService, PaperRepository paperRepository) {
        this.boardService = boardService;
        this.paperRepository = paperRepository;
    }

    public PaperSaveResponse savePaper(PaperSaveRequest request) {
        Board board = boardService.getBoard(request.boardId());
        Paper paper = new Paper(board, request.name(), request.content());
        Paper savedPaper = paperRepository.save(paper);

        return PaperSaveResponse.from(savedPaper);
    }

    public PaperDetailResponse getPaperDetail(Long paperId) {
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(() -> new PaperException(PaperErrorCode.NOT_FOUND));

        return PaperDetailResponse.from(paper);
    }

    public PaperDetailResponses getPaperDetails(String boardId) {
        List<Paper> papers = paperRepository.findAllByBoardAccessAddress(boardId);
        List<PaperDetailResponse> responses = papers.stream()
                .map(PaperDetailResponse::from)
                .toList();

        return new PaperDetailResponses(responses);
    }

    public PaperDetailPaginationResponse getPaperDetailPagination(String boardId, Long cursor, int limit) {
        Board board = boardService.getBoardByAccessAddress(boardId);
        Pageable pageable = PageRequest.of(0, limit + 1);
        List<Paper> papers = findPaginatedPapers(board.getId(), cursor, pageable);

        boolean hasNext = papers.size() > limit;
        if (hasNext) {
            papers.remove(papers.size() - 1);
        }

        List<PaperDetailResponse> responses = papers.stream()
                .map(PaperDetailResponse::from)
                .toList();

        return new PaperDetailPaginationResponse(responses, hasNext, cursor, getNextCursor(hasNext, papers));
    }

    private List<Paper> findPaginatedPapers(Long boardId, Long cursor, Pageable pageable) {
        if (cursor == null) {
            return paperRepository.findByBoardIdOrderByIdAsc(boardId, pageable);
        }
        return paperRepository.findByBoardIdAndIdGreaterThanOrderByIdAsc(boardId, cursor, pageable);
    }

    private Long getNextCursor(boolean hasNext, List<Paper> papers) {
        if (hasNext) {
            return papers.get(papers.size() - 1).getId() + 1;
        }
        return null;
    }
}
