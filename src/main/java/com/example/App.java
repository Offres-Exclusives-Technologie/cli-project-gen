package com.example;

import java.io.IOException;

import com.example.commands.AppCommands;

import freemarker.template.TemplateException;
import picocli.CommandLine;


public class App 
{
    public static void main( String[] args ) throws IOException, TemplateException
    {
        int exitCode = new CommandLine(new AppCommands()).execute(
            "-i" , "/home/elbcir/app-generator/cli-project/datamodel.yml",
            "-o" , "/home/elbcir/app-generator/projects"
        );
        System.exit(exitCode);

    }
}
