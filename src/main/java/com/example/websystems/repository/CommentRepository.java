package com.example.websystems.repository;

import com.example.websystems.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByArticle_ArticleIdOrderByCreatedAtAsc(Integer articleId);
}
