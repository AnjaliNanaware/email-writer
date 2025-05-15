# Email Writer Project

This repository contains the code for an Email Writer extension and its associated Spring Boot backend.

## Overview

This project aims to provide a tool that assists users in writing emails more efficiently. It includes a browser extension to integrate with email platforms (like Gmail) and a backend API to handle tasks such as generating email content using AI.

## Repository Structure

The repository is organized into the following main directories:

* **.idea/:** Contains IntelliJ IDEA project-specific configuration files.
* **email-writer-ext/:** Contains the source code and manifest for the browser extension.
* **email-writer-sb/:** Contains the source code for the Spring Boot backend application.

### email-writer-ext

This directory holds the files for the browser extension:

* **content.css:** (Currently empty) Likely intended for styling the extension's UI elements within the web page.
* **content.js:** Contains the JavaScript code that interacts with the web page (e.g., Gmail), injects UI elements, and communicates with the backend.
* **manifest.json:** Defines the extension's metadata, permissions, and content scripts.

### email-writer-sb

This directory contains the Spring Boot backend application:

* **.gitattributes:** Specifies attributes for files in the Git repository.
* **.gitignore:** Specifies intentionally untracked files that Git should ignore.
* **.mvn/:** Contains Maven wrapper configuration.
* **mvnw, mvnw.cmd:** Maven wrapper scripts for building the project.
* **pom.xml:** Maven project configuration file, defining dependencies and build settings.
* **src/:** Contains the source code for the Spring Boot application.
    * **src/main/java/com/email/writer/:** Contains the main Java source code.
        * **EmailWriterSbApplication.java:** The main entry point for the Spring Boot application.
        * **app/:** Contains the application-specific logic.
            * **EmailGeneratorController.java:** Handles incoming API requests for email generation.
            * **EmailGeneratorService.java:** Contains the business logic for generating email content (likely involving an AI model).
            * **EmailRequest.java:** Defines the structure for the email content request sent to the backend.
    * **src/main/resources/:** Contains application resources, such as:
        * **application.properties:** Configuration properties for the Spring Boot application.
    * **src/test/java/com/email/writer/:** Contains the test Java source code.
        * **EmailWriterSbApplicationTests.java:** Contains integration tests for the Spring Boot application.

## Technologies Used

Based on the file structure, the following technologies are likely used in this project:

* **JavaScript:** For the browser extension's functionality.
* **CSS:** For styling the browser extension (though currently empty).
* **JSON:** For the extension's manifest and API communication.
* **Java:** For the backend application.
* **Spring Boot:** A Java framework for building the backend application.
* **Maven:** A build automation tool used for managing the Spring Boot project.

## Getting Started (Conceptual)

To get this project running locally, you would typically need to:

1.  **Set up the Spring Boot backend:**
    * Navigate to the `email-writer-sb` directory.
    * Use Maven to build and run the application (e.g., `./mvnw spring-boot:run`).
    * Ensure the backend API is accessible on the configured port (likely `localhost:8085` as seen in `content.js`).

2.  **Load the browser extension:**
    * Navigate to your browser's extensions page (e.g., `chrome://extensions/` in Chrome).
    * Enable "Developer mode".
    * Click "Load unpacked" and select the `email-writer-ext` directory.

Once both the backend and the extension are running, the extension should be able to interact with email platforms and communicate with the backend API to provide email writing assistance.

**Note:** This is a high-level overview based on the provided file structure. Specific setup instructions and API details might be found in further documentation or within the code itself.
