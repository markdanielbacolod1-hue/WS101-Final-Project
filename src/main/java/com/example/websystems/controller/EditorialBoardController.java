package com.example.websystems.controller;

import com.example.websystems.repository.EditorialBoardRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditorialBoardController {

    private final EditorialBoardRepository editorialBoardRepository;

    public EditorialBoardController(EditorialBoardRepository editorialBoardRepository) {
        this.editorialBoardRepository = editorialBoardRepository;
    }

    @GetMapping("/editorial-board")
    public String editorialBoard(Model model, HttpSession session) {

        // OPTIONAL: protect page (remove if not needed)
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("boards", editorialBoardRepository.findAll());

        return "Editorial-board"; // 🔴 MUST MATCH HTML FILE NAME
    }
}
