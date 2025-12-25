package com.example.websystems.repository;
import java.util.List;
import com.example.websystems.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findByStatus(String status);

    List<Article> findByStatusAndTitleContainingIgnoreCaseOrStatusAndContentContainingIgnoreCase(
            String status1, String title,
            String status2, String content
    );
    List<Article> findByStatusAndCategory_CategoryId(String status, Integer categoryId);

    List<Article> findByStatusAndCategory_CategoryIdAndTitleContainingIgnoreCaseOrStatusAndCategory_CategoryIdAndContentContainingIgnoreCase(
            String status1, Integer categoryId1, String title,
            String status2, Integer categoryId2, String content
    );
}
