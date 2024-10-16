package com.example.commands;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.example.generators.SpringBootGenerator;

import freemarker.template.TemplateException;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "AppGen CLI", mixinStandardHelpOptions = true, version = "first 1.0", description = "First version of source code app generation by: abdelhadi el bcir")
public class AppCommands implements Callable<Integer> {

    @Option(names = { "-t", "--technology" }, description = "Technology to generate Spring Boot app base code.")
    private String technology;

    @Option(names = { "-i", "--input" }, required = true, description = "YAML file path.")
    private String inputPath;

    @Option(names = { "-o", "--output" }, required = true, description = "Output directory - project name.")
    private String outputPath;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Available options are:")
    private boolean helpRequested = false;

    @Option(names = { "-d", "--docker" }, description = "Flag to generate docker file.")
    private boolean docker;

    @Option(names = { "-s", "--security" }, description = "Flag to generate spring security.")
    private boolean security;

    @Option(names = { "-m", "--mysql" }, description = "Flag to generate mysql config.")
    private boolean mysql;

    @Option(names = { "-m", "--mongodb" }, description = "Flag to generate mongodb config.")
    private boolean mongodb;

    @Override
    public Integer call() throws Exception {
        // Check if input and output are provided
        if (inputPath == null || inputPath.isEmpty()) {
            System.err.println("Error: Input YAML file path is required.");
            return 1;
        }

        if (outputPath == null || outputPath.isEmpty()) {
            System.err.println("Error: Output directory/project name is required.");
            return 1;
        }

        switch (technology) {
            case "springboot":
                generateSpringBoot(docker, security, mysql);
                break;
            case "node":
                generateNodeJS(docker, security, mysql);
                break;
            default:
                generateSpringBoot(docker, security, mysql);
                break;
        }
            
        
        return 0;
    }

    private void generateSpringBoot(boolean docker, boolean security, boolean mysql) throws IOException, TemplateException {
        if (docker) {
            (new SpringBootGenerator(outputPath, inputPath , true , false , false)).generate();
        } else if(mysql){
            (new SpringBootGenerator(outputPath, inputPath , false , false , true)).generate();
        } else if(security){
            (new SpringBootGenerator(outputPath, inputPath , false , true , false)).generate();
        }else {
            (new SpringBootGenerator(outputPath, inputPath , false, false, false)).generate();
        }
    }


    private void generateNodeJS(boolean docker, boolean security, boolean mysql) throws IOException, TemplateException {
        if (docker) {
            (new SpringBootGenerator(outputPath, inputPath , true , false , false)).generate();
        } else if(mysql){
            (new SpringBootGenerator(outputPath, inputPath , false , false , true)).generate();
        } else if(security){
            (new SpringBootGenerator(outputPath, inputPath , false , true , false)).generate();
        }else {
            (new SpringBootGenerator(outputPath, inputPath , false, false, false)).generate();
        }
    }

}
