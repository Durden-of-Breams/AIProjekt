package org.example.aiprojekttest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String frontPage() {
        return "redirect:/frontend/index.html";
    }
}
