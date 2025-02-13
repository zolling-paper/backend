package com.zollingpaper.backend.board.repository;

import java.util.Optional;
import com.zollingpaper.backend.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByAccessAddress(String boardId);

    boolean existsBoardByAccessAddress(String accessAddress);
}
