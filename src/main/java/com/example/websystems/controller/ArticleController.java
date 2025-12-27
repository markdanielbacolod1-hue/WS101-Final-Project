//Article Workflow & Dashboard  - Catherine G. Casinas
//Create article submission page and controller- Catherine Casinas
//Save submitted articles with pending status- Catherine Casinas
//Add approve and reject actions for articles - Catherine Casinas
package com.example.websystems.controller;

import com.example.websystems.entity.Article;
import com.example.websystems.entity.Comment;
import com.example.websystems.repository.ArticleRepository;
import com.example.websystems.repository.CommentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleController(ArticleRepository articleRepository,
                             CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/articles/submit")
    public String submitPage(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return "submit-article";
    }

    @PostMapping("/articles/submit")
    public String submitArticle(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String language,
            @RequestParam Integer categoryId,
            HttpSession session
    ) {
        Integer userId = (Integer) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        if (userId == null) {
            return "redirect:/login";
        }

        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setLanguage(language);
        article.setCategoryId(categoryId);
        article.setAuthorId(userId);
        article.setStatus("pending");
        article.setDatePosted(LocalDate.now());

        articleRepository.save(article);

        return "Editor-in-chief".equals(role)
                ? "redirect:/dashboard"
                : "redirect:/articles/submit";
    }

    @PostMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Integer id, HttpSession session) {

        if (!"Editor-in-chief".equals(session.getAttribute("role"))) {
            return "redirect:/articles/submit";
        }

        articleRepository.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/articles/{id}")
    public String viewArticle(@PathVariable Integer id, Model model) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        if (!"approved".equals(article.getStatus())) {
            return "redirect:/feed";
        }

        model.addAttribute("article", article);
        model.addAttribute(
                "comments",
                commentRepository.findByArticle_ArticleIdOrderByCreatedAtAsc(id)
        );

        return "article-view";
    }

    @PostMapping("/articles/{id}/comment")
    public String addComment(
            @PathVariable Integer id,
            @RequestParam String nickname,
            @RequestParam String content
    ) {
        Comment comment = new Comment();
        comment.setArticle(articleRepository.findById(id).orElseThrow());
        comment.setNickname(nickname);
        comment.setContent(content);
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return "redirect:/articles/" + id;
    }
}
