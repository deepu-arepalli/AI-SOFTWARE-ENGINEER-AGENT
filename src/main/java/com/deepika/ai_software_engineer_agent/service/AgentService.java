package com.deepika.ai_software_engineer_agent.service;
import com.deepika.ai_software_engineer_agent.tool.FileReadTool;
import com.deepika.ai_software_engineer_agent.tool.ProjectStructureTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.deepika.ai_software_engineer_agent.tool.FileWriteTool;

@Service
public class AgentService {
    private final ChatClient chatClient;
    private final ProjectStructureTool projectStructureTool;
    private final FileReadTool fileReadTool;
    private final FileWriteTool fileWriteTool;

    public AgentService(
            ChatClient.Builder builder,
            ProjectStructureTool projectStructureTool,
            FileReadTool fileReadTool,
            FileWriteTool fileWriteTool) {

        this.chatClient = builder.build();
        this.projectStructureTool = projectStructureTool;
        this.fileReadTool = fileReadTool;
        this.fileWriteTool = fileWriteTool;
    }

    public String ask(String question) {

        String systemPrompt = """
            You are an AI Software Engineer Agent.
            Help users with software development tasks.
            You can explain code, identify bugs, suggest fixes,
            and help solve programming problems.
            Give technically accurate and practical answers.
            
            When using FileWriteTool, always provide the actual relative file path
            including the filename. For example, use "agent-test.txt", not "project-root".
            
            """;

        return chatClient
                .prompt()
                .system(systemPrompt)
                .user(question)
                .tools(projectStructureTool, fileReadTool, fileWriteTool)
                .call()
                .content();
    }
}
