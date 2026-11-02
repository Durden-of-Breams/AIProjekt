package org.example.aiprojekttest.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RoastService {

    private final ChatClient chatClient;

    public RoastService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public String demo() {

        return chatClient.prompt().system("Roast my idea and explain why it is stupid. Keep it short, less than 100 words!")
                .user("I would like to create a mobile app where you can take a picture and turn it into pixel art.")
                .call()
                .content();
    }
}