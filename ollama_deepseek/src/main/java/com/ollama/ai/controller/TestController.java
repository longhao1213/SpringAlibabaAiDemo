package com.ollama.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ChatClient chatClient;

    public TestController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat(String input) {
        return this.chatClient.prompt()
                .user(input)
                .call()
                .content();
    }

}
