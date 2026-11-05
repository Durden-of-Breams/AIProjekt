package org.example.aiprojekttest.controller;


import org.example.aiprojekttest.DTOs.RoastRequest;
import org.example.aiprojekttest.DTOs.RoastResponse;
import org.example.aiprojekttest.model.Roast;
import org.example.aiprojekttest.service.RoastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AiApiController {

    @Autowired
    private final RoastService roastService;

    public AiApiController(RoastService roastService) {
        this.roastService = roastService;
    }

    @PostMapping("/api/roast/demo")
    public RoastResponse demo(@RequestBody RoastRequest roastRequest) {
        return roastService.demo(roastRequest);
    }

    //Test metoder:

    @GetMapping("/findall")
    public List<Roast> getAllRoasts() {
        return roastService.findAllRoasts();
    }
}