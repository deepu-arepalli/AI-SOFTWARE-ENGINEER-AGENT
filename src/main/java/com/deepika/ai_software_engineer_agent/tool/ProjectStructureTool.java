package com.deepika.ai_software_engineer_agent.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ProjectStructureTool {
    @Tool(description = "Returns the file and folder structure of the software project")
    public String getProjectStructure() {
        File projectDirectory = new File(".");
        return buildStructure(projectDirectory, "");
    }

    private String buildStructure(File directory, String indentation) {

        StringBuilder structure = new StringBuilder();

        File[] files = directory.listFiles();

        if (files == null) {
            return "";
        }

        for (File file : files) {

            if (file.getName().equals(".git")
                    || file.getName().equals(".idea")
                    || file.getName().equals("target")) {
                continue;
            }

            structure.append(indentation)
                    .append(file.getName())
                    .append("\n");

            if (file.isDirectory()) {
                structure.append(
                        buildStructure(file, indentation + "    ")
                );
            }
        }

        return structure.toString();
    }
}
