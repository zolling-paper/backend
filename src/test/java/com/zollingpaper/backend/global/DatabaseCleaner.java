package com.zollingpaper.backend.global;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DatabaseCleaner {

    @PersistenceContext
    private EntityManager entityManager;

    public void execute() {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        clearPaperEntity();
        clearBoardEntity();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

    private void clearPaperEntity() {
        entityManager.createNativeQuery("DELETE FROM PAPER").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE PAPER ALTER COLUMN id RESTART").executeUpdate();
    }

    private void clearBoardEntity() {
        entityManager.createNativeQuery("DELETE FROM BOARD").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE BOARD ALTER COLUMN id RESTART").executeUpdate();
    }
}
