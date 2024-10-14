package com.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class SpringBootGenerator {
    private final String projectDir;
    private final Map<String, Object> dataModel;
    private final Configuration freemarkerConfig;

    public SpringBootGenerator(String projectDir, String yamlPath) throws IOException {
        this.projectDir = projectDir;
        this.dataModel = loadYamlFile(yamlPath);
        
        this.freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        this.freemarkerConfig.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "com/example/template");
    }

    private Map<String, Object> loadYamlFile(String yamlPath) throws IOException {
        try (InputStream inputStream = new FileInputStream(yamlPath)) {
            Yaml yaml = new Yaml();
            return yaml.load(inputStream);
        }
    }

    public void generate() throws IOException, TemplateException {
        createDirectories();
        generateFile("pom.xml.ftl", "pom.xml");
        generateFile("application.properties.ftl", "src/main/resources/application.properties");

        // Entrypoint
        generateEntryPoint("Application.java.ftl");


        List<Map<String, Object>> entities = (List<Map<String, Object>>) dataModel.get("entities");
        for (Map<String, Object> entity : entities) {
            String className = (String) entity.get("name");
            dataModel.put("currentEntity", entity);
            generateComponent("service", "Service.java.ftl", className);
            generateComponent("serviceImpl", "ServiceImpl.java.ftl", className);
            generateComponent("controller", "Controller.java.ftl", className);
            generateComponent("entity", "Entity.java.ftl", className);
            generateComponent("dto", "DTO.java.ftl", className);
            generateComponent("repository", "Repository.java.ftl", className);
            generateComponent("converter", "Converter.java.ftl", className);
        }
    }

    private void createDirectories() throws IOException {
        String packagePath = ((String) dataModel.get("packageName")).replace('.', '/');
        Files.createDirectories(Paths.get(projectDir, "src/main/java", packagePath));
        Files.createDirectories(Paths.get(projectDir, "src/main/resources"));
        Files.createDirectories(Paths.get(projectDir, "src/test/java", packagePath));
        Files.createDirectories(Paths.get(projectDir, "src/test/resources"));
    }

    private void generateEntryPoint(String templateName) throws IOException, TemplateException {
        String packagePath = ((String) dataModel.get("packageName")).replace('.', '/');
        String outputPath = String.format("src/main/java/%s/%s.java", 
            packagePath, "Application");
        generateFile(templateName, outputPath);
    }

    private void generateFile(String templateName, String outputPath) throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate(templateName);
        String fullOutputPath = Paths.get(projectDir, outputPath).toString();
        
        Files.createDirectories(Paths.get(fullOutputPath).getParent());
        
        try (Writer writer = new FileWriter(fullOutputPath)) {
            template.process(dataModel, writer);
        }
    }

    private void generateComponent(String componentType, String templateName, String className) throws IOException, TemplateException {
        String packagePath = ((String) dataModel.get("packageName")).replace('.', '/');
        String outputPath = String.format("src/main/java/%s/%s/%s%s.java", 
            packagePath, componentType, className, componentType.substring(0, 1).toUpperCase() + componentType.substring(1));
        generateFile(templateName, outputPath);
    }
}