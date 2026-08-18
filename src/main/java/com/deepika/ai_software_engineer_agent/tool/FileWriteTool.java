package com.deepika.ai_software_engineer_agent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileWriteTool {

    @Tool(description = """
        Creates a new file inside the project.
        filePath must be the actual relative file path and filename,
        for example 'agent-test.txt' or 'src/main/resources/test.txt'.
        Do not use words like 'project-root' as the filePath.
        The content parameter must contain the complete content to write.
        """)
    public String writeFile(String filePath, String content) {

        System.out.println("FileWriteTool called");
        System.out.println("filePath = " + filePath);
        System.out.println("content = " + content);
        try {
            Path projectRoot = Path.of(".").toAbsolutePath().normalize();

            String normalizedFilePath = filePath;

            if (normalizedFilePath.startsWith("project-root/")) {
                normalizedFilePath = normalizedFilePath.substring("project-root/".length());
            }

            Path requestedPath = projectRoot.resolve(normalizedFilePath).normalize();

            if (!requestedPath.startsWith(projectRoot)) {
                return "Access denied: file is outside the project directory.";
            }

            if (Files.exists(requestedPath)) {
                return "File already exists. Writing to existing files is not allowed yet: " + filePath;
            }

            Files.writeString(requestedPath, content);

            return "File created successfully: " + filePath;

        } catch (IOException e) {
            return "Could not write file: " + e.getMessage();
        }
    }
}