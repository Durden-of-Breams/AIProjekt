package org.example.aiprojekttest.controller;


import org.example.aiprojekttest.service.RoastService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoastController {
    private final RoastService roastService;

    public RoastController(RoastService roastService) {
        this.roastService = roastService;
    }

    @PostMapping("/api/roast/demo")
    public String demo() {
        return roastService.demo();
    }
}