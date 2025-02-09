package com.zollingpaper.backend.board.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import com.zollingpaper.backend.global.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 36, unique = true)
    private String accessAddress;

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

    private Board(Long id, String accessAddress, String name, String password, LocalDateTime showDate) {
        this.id = id;
        this.accessAddress = accessAddress;
        this.name = name;
        this.password = password;
        this.showDate = showDate;
    }

    public Board(String accessAddress, String name, String password, LocalDateTime showDate) {
        this(null, accessAddress, name, password, showDate);
    }

    public boolean isPublic() {
        return showDate.isEqual(LocalDateTime.now()) || showDate.isBefore(LocalDateTime.now());
    }

    public int calculateRemainingDays() {
        if (showDate.isBefore(LocalDateTime.now())) {
            return 0;
        }

        LocalDateTime now = LocalDateTime.now();
        long remainingDays = ChronoUnit.DAYS.between(now.toLocalDate(), showDate.toLocalDate());

        return (int) remainingDays;
    }

    public Long getId() {
        return id;
    }

    public String getAccessAddress() {
        return accessAddress;
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
