package org.example.aiprojekttest.service;

import org.example.aiprojekttest.model.Idea;
import org.example.aiprojekttest.repository.IdeaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public Idea createIdea(String ideaText) {
        return ideaRepository.save(new Idea(ideaText));
    }

    public Idea getIdeaById(long ideaId) {
        return ideaRepository.findById(ideaId)
                .orElseThrow(() -> new IllegalArgumentException("Idea not found: " + ideaId));
    }

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    public Idea saveIdea(Idea idea) {
        return ideaRepository.save(idea);
    }

    public void deleteIdea(long ideaId) {
        ideaRepository.deleteById(ideaId);
    }
}