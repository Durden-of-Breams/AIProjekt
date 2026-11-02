package org.example.aiprojekttest.service;

import org.example.aiprojekttest.DTOs.RoastRequest;
import org.example.aiprojekttest.DTOs.RoastResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RoastService {

    private final String instructions = "Roast my idea brutally and explain why it is flawed, impractical, or stupid. Be direct, witty, and unapologetically critical. Do not sugarcoat your feedback or try to be overly encouraging." +
            "Rate the idea's stupidity on a scale from 1.0 to 10.0, where 1.0 means perfectly reasonable and 10.0 means unbelievably stupid." +
            "Keep your response concise and under 100 words!";

    private final ChatClient chatClient;

    public RoastService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public RoastResponse demo(RoastRequest roastRequest) {

        return chatClient.prompt().system(instructions)
                .user(roastRequest.roastBody())
                .call()
                .entity(RoastResponse.class, spec -> spec.useProviderStructuredOutput());
    }
}