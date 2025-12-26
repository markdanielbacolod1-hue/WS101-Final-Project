package com.example.websystems.controller;

import com.example.websystems.entity.User;
import com.example.websystems.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }

        session.setAttribute("userId", user.getUserId());
        session.setAttribute("role", user.getRole());
        session.setAttribute("email", user.getEmail());

        if ("Editor-in-chief".equals(user.getRole())) {
            return "redirect:/dashboard";
        }

        return "redirect:/articles/submit";
    }
}
