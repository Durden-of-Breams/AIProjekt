package org.example.aiprojekttest.controller;

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
    public ResponseEntity<IdeaResponse> createIdea(@RequestBody CreateIdeaRequest request) {
        Idea idea = ideaService.createIdea(request.ideaText(), request.userId());

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(idea));
    }

    @GetMapping("/{ideaId}")
    public ResponseEntity<IdeaResponse> getIdeaById(@PathVariable long ideaId) {
        Idea idea = ideaService.getIdeaById(ideaId);
        return ResponseEntity.ok(toResponse(idea));
    }

    @GetMapping
    public ResponseEntity<List<IdeaResponse>> getAllIdeas() {
        List<IdeaResponse> ideas = ideaService.getAllIdeas()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(ideas);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<IdeaResponse>> getIdeasByUserId(
            @PathVariable long userId) {

        List<IdeaResponse> ideas = ideaService.getIdeasByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(ideas);
    }

    @PutMapping("/{ideaId}")
    public ResponseEntity<IdeaResponse> updateIdea(@PathVariable long ideaId, @RequestBody UpdateIdeaRequest request) {

        Idea idea = ideaService.getIdeaById(ideaId);
        idea.setIdea(request.ideaText());

        Idea updatedIdea = ideaService.saveIdea(idea);

        return ResponseEntity.ok(toResponse(updatedIdea));
    }

    @DeleteMapping("/{ideaId}")
    public ResponseEntity<Void> deleteIdea(
            @PathVariable long ideaId) {

        ideaService.deleteIdea(ideaId);
        return ResponseEntity.noContent().build();
    }

    private IdeaResponse toResponse(Idea idea) {
        return new IdeaResponse(idea.getId(), idea.getIdea(), idea.getUser().getId());
    }

    public record CreateIdeaRequest(String ideaText, long userId) {
    }

    public record UpdateIdeaRequest(String ideaText) {
    }

    public record IdeaResponse(Long id, String idea, Long userId) {
    }
}