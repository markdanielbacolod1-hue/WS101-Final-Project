package com.example.websystems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage(Model model) {

        model.addAttribute("announcements", List.of());

        return "homepage";
    }
}
