package org.example.aiprojekttest.service;

import org.example.aiprojekttest.DTOs.RoastDTO;
import org.example.aiprojekttest.DTOs.RoastRequest;
import org.example.aiprojekttest.DTOs.RoastResponse;
import org.example.aiprojekttest.model.Idea;
import org.example.aiprojekttest.model.Roast;
import org.example.aiprojekttest.repository.IdeaRepository;
import org.example.aiprojekttest.repository.RoastRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoastService {

    private static final String INSTRUCTIONS =
            "Roast my idea brutally and explain why it is flawed, impractical, or stupid. " +
                    "Be direct, witty, and unapologetically critical. " +
                    "Do not sugarcoat your feedback or try to be overly encouraging. " +
                    "Rate the idea's stupidity on a scale from 1.0 to 10.0, where 1.0 means perfectly reasonable " +
                    "and 10.0 means unbelievably stupid. " +
                    "Keep your response concise and under 100 words!";

    private final RoastRepository roastRepository;
    private final IdeaRepository ideaRepository;
    private final ChatClient chatClient;

    public RoastService(RoastRepository roastRepository,
                        IdeaRepository ideaRepository,
                        ChatClient.Builder builder) {
        this.roastRepository = roastRepository;
        this.ideaRepository = ideaRepository;
        this.chatClient = builder.build();
    }

    // Generates a roast without saving anything
    public RoastResponse demo(RoastRequest request) {
        return chatClient.prompt()
                .system(INSTRUCTIONS)
                .user(request.roastBody())
                .call()
                .entity(RoastResponse.class, spec -> spec.useProviderStructuredOutput());
    }

    public RoastDTO generateAndSaveRoast(RoastRequest request) {
        RoastResponse response = demo(request);

        Idea idea = new Idea(request.roastBody());
        Roast roast = new Roast(response.roastBody(), response.roastScore(), null);
        idea.addRoast(roast);

        Idea savedIdea = ideaRepository.save(idea);

        return new RoastDTO(
                roast.getId(),
                savedIdea.getIdea(),
                roast.getRoast(),
                roast.getRoastScore()
        );
    }

    public List<RoastDTO> findAllRoasts() {
        return roastRepository.findAll()
                .stream()
                .map(r -> new RoastDTO(
                        r.getId(),
                        r.getIdea().getIdea(),
                        r.getRoast(),
                        r.getRoastScore()))
                .toList();
    }
}