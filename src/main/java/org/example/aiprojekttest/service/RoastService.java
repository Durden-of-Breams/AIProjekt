package org.example.aiprojekttest.service;

import jakarta.transaction.Transactional;
import org.example.aiprojekttest.DTOs.RoastRequest;
import org.example.aiprojekttest.DTOs.RoastResponse;
import org.example.aiprojekttest.model.Idea;
import org.example.aiprojekttest.model.Roast;
import org.example.aiprojekttest.repository.IdeaRepository;
import org.example.aiprojekttest.repository.RoastRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoastService {

    private final String instructions = "Roast my idea brutally and explain why it is flawed, impractical, or stupid. Be direct, witty, and unapologetically critical. Do not sugarcoat your feedback or try to be overly encouraging." +
            "Rate the idea's stupidity on a scale from 1.0 to 10.0, where 1.0 means perfectly reasonable and 10.0 means unbelievably stupid." +
            "Keep your response concise and under 100 words!";


    private final RoastRepository roastRepository;
    private final IdeaRepository ideaRepository;
    private final ChatClient chatClient;

    public RoastService(RoastRepository roastRepository, IdeaRepository ideaRepository, ChatClient.Builder builder) {
        this.roastRepository = roastRepository;
        this.ideaRepository = ideaRepository;
        this.chatClient = builder.build();
    }

    public RoastResponse demo(RoastRequest roastRequest) {

        return chatClient.prompt().system(instructions)
                .user(roastRequest.roastBody())
                .call()
                .entity(RoastResponse.class, spec -> spec.useProviderStructuredOutput());
    }


    @Transactional
    public void addRoastToIdea(Roast roast, long ideaId) {
        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new IllegalArgumentException("Idea not found: " + ideaId));

        idea.addRoast(roast);
        ideaRepository.save(idea);
    }


    public Roast saveRoast(Roast roast) {
        return roastRepository.save(roast);
    }

    @Transactional
    public void deleteRoast(long roastId) {
        Roast roast = roastRepository.findById(roastId)
                .orElseThrow(() -> new IllegalArgumentException("Roast not found: " + roastId));

        Idea idea = roast.getIdea();

        if (idea != null) {
            idea.removeRoast(roast);
        }
    }

    @Transactional
    public Roast createRoast(Roast roast, long ideaId) {
        Idea idea = ideaRepository.findById(ideaId)
                .orElseThrow(() -> new IllegalArgumentException("Idea not found: " + ideaId));

        idea.addRoast(roast);
        ideaRepository.save(idea);
        return roast;
    }
}