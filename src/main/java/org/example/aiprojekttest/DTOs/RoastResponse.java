package org.example.aiprojekttest.DTOs;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record RoastResponse(String roastBody, @JsonPropertyDescription("Use exactly one decimal place.") double roastScore) {
}
