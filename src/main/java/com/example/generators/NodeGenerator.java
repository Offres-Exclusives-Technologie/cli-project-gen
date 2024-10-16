package com.example.generators;

import org.yaml.snakeyaml.Yaml;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class NodeGenerator {
    
    private final String projectDir;
    private final Map<String, Object> dataModel;
    private final Configuration freemarkerConfig;
    private boolean docker = false;
    private boolean security = false;
    private boolean mysql = false;

    public NodeGenerator(String projectDir, String yamlPath, boolean docker, boolean security, boolean mysql) throws IOException {
        this.projectDir = projectDir;
        this.dataModel = loadYamlFile(yamlPath);
        this.docker = docker;
        this.security = security;
        this.mysql = mysql;
        
        this.freemarkerConfig = new Configuration(Configuration.VERSION_2_3_31);
        this.freemarkerConfig.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "templates/nodeJSTemplate");
    }

    private Map<String, Object> loadYamlFile(String yamlPath) throws IOException {
        try (InputStream inputStream = new FileInputStream(yamlPath)) {
            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);
            data.put("useDocker", docker);
            data.put("useSecurity", security);
            data.put("useMysql", mysql);
            return data;
        }
    }

    public void generate() throws IOException, TemplateException {
        // Create project structure
        createProjectStructure();

        // Generate core files
        generateCoreFiles();

        // Generate configuration files
        generateConfigFiles();

        // Generate components for each entity
        List<Map<String, Object>> entities = (List<Map<String, Object>>) dataModel.get("entities");
        for (Map<String, Object> entity : entities) {
            generateEntityComponents(entity);
        }

        // Generate Docker files if needed
        if (docker) {
            generateDockerFiles();
        }
    }

    private void createProjectStructure() throws IOException {
        String[] directories = {
            "src",
            "src/models",
            "src/controllers",
            "src/routes",
            "src/services",
            "src/middleware",
            "src/config",
            "src/utils",
            "src/validators",
            "src/tests",
            "src/tests/unit",
            "src/tests/integration"
        };

        for (String dir : directories) {
            Files.createDirectories(Paths.get(projectDir, dir));
        }
    }

    private void generateCoreFiles() throws IOException, TemplateException {
        generateFile("package.json.ftl", "package.json");
        generateFile("app.js.ftl", "src/app.js");
        generateFile("env.ftl", ".env");
        generateFile("gitignore.ftl", ".gitignore");
        generateFile("readme.ftl", "README.md");
    }

    private void generateConfigFiles() throws IOException, TemplateException {
        generateFile("database.js.ftl", "src/config/database.js");
        if (security) {
            generateFile("auth.js.ftl", "src/config/auth.js");
        }
        generateFile("logger.js.ftl", "src/config/logger.js");
    }

    private void generateEntityComponents(Map<String, Object> entity) throws IOException, TemplateException {
        dataModel.put("currentEntity", entity);
        String entityName = (String) entity.get("name");

        // Generate model
        generateFile("models/model.ftl", 
            String.format("src/models/%s.js", entityName));

        // Generate controller
        generateFile("controllers/controller.ftl", 
            String.format("src/controllers/%sController.js", entityName));

        // Generate service
        // generateFile("services/service.ftl", 
        //     String.format("src/services/%sService.js", entityName));

        // Generate route
        generateFile("routes/route.ftl", 
            String.format("src/routes/%sRoutes.js", entityName));

        // Generate validator
        generateFile("validators/validator.ftl", 
            String.format("src/validators/%sValidator.js", entityName));

        // Generate tests
        // generateFile("tests/unit/model.test.ftl", 
        //     String.format("src/tests/unit/%sModel.test.js", entityName));
        // generateFile("tests/unit/service.test.ftl", 
        //     String.format("src/tests/unit/%sService.test.js", entityName));
        // generateFile("tests/integration/api.test.ftl", 
        //     String.format("src/tests/integration/%sApi.test.js", entityName));
    }

    private void generateDockerFiles() throws IOException, TemplateException {
        generateFile("docker/Dockerfile.ftl", "Dockerfile");
        generateFile("docker/docker-compose.yml.ftl", "docker-compose.yml");
    }

    private void generateFile(String templateName, String outputPath) throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate(templateName);
        String fullOutputPath = Paths.get(projectDir, outputPath).toString();
        
        Files.createDirectories(Paths.get(fullOutputPath).getParent());
        
        try (Writer writer = new FileWriter(fullOutputPath)) {
            template.process(dataModel, writer);
        }
    }
}