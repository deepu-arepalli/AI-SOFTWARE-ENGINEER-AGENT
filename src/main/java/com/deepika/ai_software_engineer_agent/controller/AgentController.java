package com.deepika.ai_software_engineer_agent.controller;

import com.deepika.ai_software_engineer_agent.service.AgentService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return agentService.ask(question);
    }
}
