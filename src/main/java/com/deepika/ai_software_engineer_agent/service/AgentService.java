package com.deepika.ai_software_engineer_agent.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    private final ChatClient chatClient;

    public AgentService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String ask(String question) {

        String systemPrompt = """
            You are an AI Software Engineer Agent.
            Help users with software development tasks.
            You can explain code, identify bugs, suggest fixes,
            and help solve programming problems.
            Give technically accurate and practical answers.
            """;

        return chatClient
                .prompt()
                .system(systemPrompt)
                .user(question)
                .call()
                .content();
    }
}
