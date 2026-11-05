package org.example.aiprojekttest.controller;

import org.example.aiprojekttest.DTOs.IdeaDTO;
import org.example.aiprojekttest.DTOs.IdeaRequest;
import org.example.aiprojekttest.model.Idea;
import org.example.aiprojekttest.service.IdeaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping
    public ResponseEntity<IdeaDTO> createIdea(@RequestBody IdeaRequest request) {
        Idea idea = ideaService.createIdea(request.ideaText());
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(idea));
    }

    @GetMapping("/{ideaId}")
    public ResponseEntity<IdeaDTO> getIdeaById(@PathVariable long ideaId) {
        return ResponseEntity.ok(toDto(ideaService.getIdeaById(ideaId)));
    }

    @GetMapping
    public ResponseEntity<List<IdeaDTO>> getAllIdeas() {
        List<IdeaDTO> ideas = ideaService.getAllIdeas()
                .stream()
                .map(this::toDto)
                .toList();

        return ResponseEntity.ok(ideas);
    }

    @PutMapping("/{ideaId}")
    public ResponseEntity<IdeaDTO> updateIdea(@PathVariable long ideaId, @RequestBody IdeaRequest request) {
        Idea idea = ideaService.getIdeaById(ideaId);
        idea.setIdea(request.ideaText());

        return ResponseEntity.ok(toDto(ideaService.saveIdea(idea)));
    }

    @DeleteMapping("/{ideaId}")
    public ResponseEntity<Void> deleteIdea(@PathVariable long ideaId) {
        ideaService.deleteIdea(ideaId);
        return ResponseEntity.noContent().build();
    }

    private IdeaDTO toDto(Idea idea) {
        return new IdeaDTO(idea.getId(), idea.getIdea());
    }
}