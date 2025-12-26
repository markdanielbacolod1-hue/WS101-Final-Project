package com.example.websystems.controller;

import com.example.websystems.repository.ArticleRepository;
import com.example.websystems.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedController {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public FeedController(ArticleRepository articleRepository,
                          CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/feed")
    public String feed(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Integer category,
            Model model) {

        model.addAttribute("categories", categoryRepository.findAll());

        if (q != null && !q.isBlank() && category != null) {
            model.addAttribute(
                    "articles",
                    articleRepository
                            .findByStatusAndCategory_CategoryIdAndTitleContainingIgnoreCaseOrStatusAndCategory_CategoryIdAndContentContainingIgnoreCase(
                                    "approved", category, q,
                                    "approved", category, q
                            )
            );
        } else if (category != null) {
            model.addAttribute(
                    "articles",
                    articleRepository.findByStatusAndCategory_CategoryId("approved", category)
            );
        } else if (q != null && !q.isBlank()) {
            model.addAttribute(
                    "articles",
                    articleRepository
                            .findByStatusAndTitleContainingIgnoreCaseOrStatusAndContentContainingIgnoreCase(
                                    "approved", q,
                                    "approved", q
                            )
            );
        } else {
            model.addAttribute(
                    "articles",
                    articleRepository.findByStatus("approved")
            );
        }

        model.addAttribute("q", q);
        model.addAttribute("selectedCategory", category);

        return "feed";
    }
}
