# SDET Task

This project is a demonstration of automated testing for a GraphQL API using Java and JUnit.

## Table of Contents

- [Requirements](#requirements)
- [Project Setup](#project-setup)
- [Building](#building)
- [Usage](#usage)

## Requirements

Make sure you have the following requirements installed:

- Java 8 or higher
- Maven 3.x.x

## Project Setup

To set up the project locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/anapasousa/sdet-task-spotted-zebra.git

2. Change to the project directory:

   ```bash
   cd sdet-task

3. Build the project:
   
   ```bash
   mvn clean install

## Building
To build the project, use the following command:
   
    mvn clean package

The compiled artifacts will be located in the target/ directory.

## Usage
To run the automated tests for the GraphQL API, follow these steps:

- Open the config.properties file located in the src/main/resources directory.

- Update the configuration settings to match your GraphQL API endpoint and any other necessary details.

- Run the tests with the following command:

   ```bash
    mvn test

The test results will be displayed in the console.
