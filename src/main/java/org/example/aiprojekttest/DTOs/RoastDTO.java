package org.example.aiprojekttest.DTOs;

public record RoastDTO(
        Long id,
        String idea,
        String roast,
        double roastScore
) {
}