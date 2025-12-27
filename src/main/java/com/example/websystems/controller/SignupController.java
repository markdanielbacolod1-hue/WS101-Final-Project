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
            @RequestParam String language_section,
            @RequestParam String first_name,
            @RequestParam(required = false) String middle_name,
            @RequestParam String last_name,
            @RequestParam(required = false) String contact,
            HttpSession session
    ) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setLanguageSection(language_section);

        user = userRepository.save(user);

        EditorialBoard board = new EditorialBoard();
        board.setFirstName(first_name);
        board.setMiddleName(middle_name);
        board.setLastName(last_name);
        board.setPosition(role);
        board.setLanguageSection(language_section);
        board.setContact(contact);

        editorialBoardRepository.save(board);

        session.setAttribute("userId", user.getUserId());
        session.setAttribute("role", user.getRole());

        return "redirect:/dashboard";
    }
}
