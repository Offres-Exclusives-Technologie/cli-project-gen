package com.example.commands;

import java.util.concurrent.Callable;

import com.example.generators.SpringBootGenerator;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "AppGen CLI", 
    mixinStandardHelpOptions = true, 
    version = "first 1.0",
    description = "First version of source code app generation by: abdelhadi el bcir"
)
public class AppCommands implements Callable<Integer> {
    
    @Option(names = {"-t", "--technology"}, description = "Technology to generate Spring Boot app base code.")
    private String technology;

    @Option(names = {"-i", "--input"}, required = true, description = "YAML file path.")
    private String inputPath;

    @Option(names = {"-o", "--output"}, required = true, description = "Output directory - project name.")
    private String outputPath;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "Available options are:")
    private boolean helpRequested = false;

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

        // Assuming SpringBootGenerator expects both input and output paths
        SpringBootGenerator generator = new SpringBootGenerator(outputPath, inputPath);
        generator.generate();

        return 0;
    }
}
