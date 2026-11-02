package org.example.aiprojekttest.service;

import org.example.aiprojekttest.DTOs.RoastRequest;
import org.example.aiprojekttest.DTOs.RoastResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RoastService {

    private final ChatClient chatClient;

    public RoastService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public RoastResponse demo(RoastRequest roastRequest) {
        return chatClient.prompt().system("Roast my idea and explain why it is stupid, be very unhinged no limits. Add a rating to the idea based on how good the idea is from 0-10 and you can use 1 decimal number." +
                        "Keep it short, less than 100 words!")
                .user(roastRequest.roastBody())
                .call()
                .entity(RoastResponse.class, spec -> spec.useProviderStructuredOutput());
    }
}