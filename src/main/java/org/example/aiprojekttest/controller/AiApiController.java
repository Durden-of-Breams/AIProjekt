package org.example.aiprojekttest.controller;


import org.example.aiprojekttest.DTOs.RoastRequest;
import org.example.aiprojekttest.DTOs.RoastResponse;
import org.example.aiprojekttest.service.RoastService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiApiController {
    private final RoastService roastService;

    public AiApiController(RoastService roastService) {
        this.roastService = roastService;
    }

    @PostMapping("/api/roast/demo")
    public RoastResponse demo(@RequestBody RoastRequest roastRequest) {
        return roastService.demo(roastRequest);
    }
}