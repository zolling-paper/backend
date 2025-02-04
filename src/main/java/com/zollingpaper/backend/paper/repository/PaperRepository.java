package com.zollingpaper.backend.paper.repository;

import java.util.List;
import com.zollingpaper.backend.paper.domain.Paper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findAllByBoardId(Long boardId);

    List<Paper> findByBoardIdOrderByIdAsc(Long boardId, Pageable pageable);

    List<Paper> findByBoardIdAndIdGreaterThanOrderByIdAsc(Long boardId, Long cursor, Pageable pageable);
}
