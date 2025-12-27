//Integrate editorial board from user data - Ma. Rinafe Lozano
package com.example.websystems.controller;

import com.example.websystems.repository.EditorialBoardRepository;
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
    public String editorialBoard(Model model) {

        model.addAttribute(
                "members",
                editorialBoardRepository.findAll()
        );

        return "editorial-board";
    }
}
