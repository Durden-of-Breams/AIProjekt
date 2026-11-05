package org.example.aiprojekttest.controller;

import org.example.aiprojekttest.DTOs.RoastDTO;
import org.example.aiprojekttest.DTOs.RoastRequest;
import org.example.aiprojekttest.DTOs.RoastResponse;
import org.example.aiprojekttest.service.RoastService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roasts")
public class RoastController {

    private final RoastService roastService;

    public RoastController(RoastService roastService) {
        this.roastService = roastService;
    }

    @PostMapping
    public ResponseEntity<RoastDTO> generateRoast(@RequestBody RoastRequest request) {
        RoastDTO roast = roastService.generateAndSaveRoast(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(roast);
    }

    @PostMapping("/demo")
    public ResponseEntity<RoastResponse> generateRoastNoSave(@RequestBody RoastRequest request) {
        return ResponseEntity.ok(roastService.demo(request));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<RoastDTO>> getAllRoasts() {
        return ResponseEntity.ok(roastService.findAllRoasts());
    }
}