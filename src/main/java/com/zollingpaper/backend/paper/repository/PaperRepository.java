package com.zollingpaper.backend.paper.repository;

import com.zollingpaper.backend.paper.domain.Paper;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper, Long> {

    List<Paper> findAllByBoardId(Long boardId);
}
