package com.example;

import java.io.IOException;

import freemarker.template.TemplateException;


public class App 
{
    public static void main( String[] args ) throws IOException, TemplateException
    {
        String projectDir = "/home/elbcir/app-generator/cli-project/output";
        String yamlPath = "/home/elbcir/app-generator/cli-project/datamodel.yml";
        
        SpringBootGenerator generator = new SpringBootGenerator(projectDir, yamlPath);
        generator.generate();
    }
}
