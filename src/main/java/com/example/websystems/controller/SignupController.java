// Handle new user registration request - mark
package com.example.websystems.controller;

import com.example.websystems.entity.EditorialBoard;
import com.example.websystems.entity.User;
import com.example.websystems.repository.EditorialBoardRepository;
import com.example.websystems.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    private final UserRepository userRepository;
    private final EditorialBoardRepository editorialBoardRepository;

    public SignupController(UserRepository userRepository,
                            EditorialBoardRepository editorialBoardRepository) {
        this.userRepository = userRepository;
        this.editorialBoardRepository = editorialBoardRepository;
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam("language_section") String languageSection,
            HttpSession session
    ) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setLanguageSection(languageSection);

        user = userRepository.save(user);

        EditorialBoard board = new EditorialBoard();
        board.setPosition(role);
        board.setLanguageSection(languageSection);

        editorialBoardRepository.save(board);

        return "redirect:/login"; // ✅ correct behavior after signup
    }

}
