
package org.example.aiprojekttest.service;

import org.example.aiprojekttest.model.Idea;
import org.example.aiprojekttest.repository.IdeaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final AppUserRepository appUserRepository;

    public IdeaService(IdeaRepository ideaRepository, AppUserRepository appUserRepository) {
        this.ideaRepository = ideaRepository;
        this.appUserRepository = appUserRepository;
    }


    @Transactional
    public Idea createIdea(String ideaText, long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        Idea idea = new Idea();
        idea.setIdea(ideaText);

        user.addIdea(idea);

        appUserRepository.save(user);
        return idea;
    }

    public Idea getIdeaById(long ideaId) {
        return ideaRepository.findById(ideaId)
                .orElseThrow(() -> new IllegalArgumentException("Idea not found: " + ideaId));
    }

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    public List<Idea> getIdeasByUserId(long userId) {
        return ideaRepository.findByAppUserId(userId);
    }

    public Idea saveIdea(Idea idea) {
        return ideaRepository.save(idea);
    }

    public void deleteIdea(long ideaId) {
        ideaRepository.deleteById(ideaId);
    }
}
