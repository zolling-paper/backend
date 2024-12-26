package com.zollingpaper.backend.paper.domain;

import com.zollingpaper.backend.board.domain.Board;
import com.zollingpaper.backend.global.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Paper extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Board board;

    @NotNull
    @Column(nullable = false, length = 32)
    private String name;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    protected Paper() {
    }

    private Paper(Long id, Board board, String name, String content) {
        this.id = id;
        this.board = board;
        this.name = name;
        this.content = content;
    }

    public Paper(Board board, String name, String content) {
        this(null, board, name, content);
    }

    public Long getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
