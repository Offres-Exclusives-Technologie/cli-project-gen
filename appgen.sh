#!/bin/bash

# Check if the correct number of arguments are provided
if [ "$#" -ne 2 ]; then
    echo "Usage: ./runApp.sh <input_yaml_path> <output_directory>"
    exit 1
fi

# Assign arguments to variables
INPUT_PATH=$1
OUTPUT_PATH=$2

# Define the generated JAR file path
JAR_FILE=target/cli-project-1.0-SNAPSHOT-jar-with-dependencies.jar

# Check if the JAR file exists
if [ ! -f "$JAR_FILE" ]; then
    echo "JAR file not found. Please ensure the build was successful."
    exit 1
fi

# Run the JAR file with input and output parameters
echo "Running the application..."
java -jar "$JAR_FILE" --input "$INPUT_PATH" --output "$OUTPUT_PATH"
