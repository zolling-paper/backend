package com.zollingpaper.backend.board.domain;

import com.zollingpaper.backend.global.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 32)
    private String name;

    @NotNull
    @Column(nullable = false, length = 4)
    private String password;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime showDate;

    protected Board() {
    }

    private Board(Long id, String name, String password, LocalDateTime showDate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.showDate = showDate;
    }

    public Board(String name, String password, LocalDateTime showDate) {
        this(null, name, password, showDate);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getShowDate() {
        return showDate;
    }
}
