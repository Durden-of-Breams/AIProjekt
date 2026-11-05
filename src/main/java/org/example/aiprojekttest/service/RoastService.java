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

    private static final String BASE_INSTRUCTIONS = """
            Roast the user's idea brutally and explain why it is flawed, impractical, or stupid.
            Be direct, witty, and unapologetically critical. Do not sugarcoat the feedback or become encouraging.
            Rate the idea's stupidity on a scale from 1.0 to 10.0, where 1.0 means perfectly reasonable and 10.0 means unbelievably stupid.
            Keep the response concise and under 100 words.
            """;

    private static final String GORDON_PERSONALITY = """
            Act like gordon ramsey use some of his classic lines.
            """;

    private static final String FURRY_PERSONALITY = """
            Write like a playful furry character. Use a lot of *nuzzles you* so the roleplay thingies. and also howl at the moon and be very cringy
            """;

    private static final String NERD_PERSONALITY = """
            Use the voice of an obsessive technical nerd. Point out broken assumptions, edge cases, scaling problems, and bad architecture with precise but entertaining jargon. Also use achtually! to say something.
            """;


    private final RoastRepository roastRepository;
    private final IdeaRepository ideaRepository;
    private final ChatClient chatClient;

    public RoastService(RoastRepository roastRepository, IdeaRepository ideaRepository, ChatClient.Builder builder) {
        this.roastRepository = roastRepository;
        this.ideaRepository = ideaRepository;
        this.chatClient = builder.build();
    }

    public RoastResponse demo(RoastRequest roastRequest) {

        return chatClient.prompt().system(BASE_INSTRUCTIONS + "\n" + personalityInstructions(roastRequest.personality()))
                .user(roastRequest.roastBody())
                .call()
                .entity(RoastResponse.class, spec -> spec.useProviderStructuredOutput());
    }

    private String personalityInstructions(String personality) {
        if (personality == null) {
            return GORDON_PERSONALITY;
        }

        return switch (personality) {
            case "furry" -> FURRY_PERSONALITY;
            case "nerd" -> NERD_PERSONALITY;
            case "fiery-chef" -> GORDON_PERSONALITY;
            default -> GORDON_PERSONALITY;
        };
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
