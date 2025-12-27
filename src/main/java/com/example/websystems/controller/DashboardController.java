//Restrict dashboard access based on user role - mark
//Implement editor dashboard for article moderation - Catherine Casinas
package com.example.websystems.controller;

import com.example.websystems.repository.ArticleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    private final ArticleRepository articleRepository;

    public DashboardController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        if (!"Editor-in-chief".equals(session.getAttribute("role"))) {
            return "redirect:/articles/submit";
        }

        model.addAttribute("articles", articleRepository.findAll());
        return "admin-dashboard";
    }

    @PostMapping("/dashboard/articles/approve/{id}")
    public String approveArticle(@PathVariable Integer id, HttpSession session) {

        if (!"Editor-in-chief".equals(session.getAttribute("role"))) {
            return "redirect:/";
        }

        articleRepository.findById(id).ifPresent(article -> {
            article.setStatus("approved");
            articleRepository.save(article);
        });

        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/articles/reject/{id}")
    public String rejectArticle(@PathVariable Integer id, HttpSession session) {

        if (!"Editor-in-chief".equals(session.getAttribute("role"))) {
            return "redirect:/";
        }

        articleRepository.findById(id).ifPresent(article -> {
            article.setStatus("rejected");
            articleRepository.save(article);
        });

        return "redirect:/dashboard";
    }
}
