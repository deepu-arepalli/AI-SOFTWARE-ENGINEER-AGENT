package com.deepika.ai_software_engineer_agent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileReadTool {
    @Tool(description = "Reads the contents of a file in the project")
    public String readFile(String filePath) {

        try {
            Path projectRoot = Path.of(".").toAbsolutePath().normalize();
            Path requestedPath = projectRoot.resolve(filePath).normalize();

            if (!requestedPath.startsWith(projectRoot)) {
                return "Access denied: file is outside the project directory.";
            }

            if (!Files.exists(requestedPath)) {
                return "File not found: " + filePath;
            }

            if (!Files.isRegularFile(requestedPath)) {
                return "Not a file: " + filePath;
            }

            return Files.readString(requestedPath);

        } catch (IOException e) {
            return "Could not read file: " + e.getMessage();
        }
    }
}
