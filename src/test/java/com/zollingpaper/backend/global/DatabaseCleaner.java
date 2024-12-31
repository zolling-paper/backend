package com.zollingpaper.backend.global;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DatabaseCleaner {

    @PersistenceContext
    private EntityManager entityManager;

    public void execute() {
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        getTruncateQueries().forEach(query -> entityManager.createNativeQuery(query).executeUpdate());
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

    private List<String> getTruncateQueries() {
        String sql = """
                SELECT CONCAT('TRUNCATE TABLE ', TABLE_NAME, ' RESTART IDENTITY',';')
                FROM INFORMATION_SCHEMA.TABLES
                WHERE TABLE_SCHEMA = 'PUBLIC'
                """;

        return entityManager.createNativeQuery(sql)
                .getResultList();
    }
}
