# Spring-AI Demo Project

This project demonstrates the integration of OpenAI's API with a Spring Boot application. It provides a hands-on example of how to leverage OpenAI's powerful language models to build intelligent applications.

## Features

- chat model
- tool calling
- image generation
- image classification
- audio transcription
- text to speech
- embeddings

---

## Prerequisites

To run this project, you will need:

1. **Java 24**: Ensure you have Java Development Kit (JDK) 24 installed on your system.
2. **OpenAI API Key**: You must have an API key to access OpenAI's services.

---

## Project Setup

Follow the steps below to get started with the Spring-AI Demo Project:

### 1. Clone the Repository

```bash
git clone https://github.com/Cyion/spring-ai-demo.git
cd spring-ai-demo
```

### 2. Add Your OpenAI API Key

In the `application.properties` file (located at `src/main/resources/application.properties`), add your OpenAI API key.

```properties
# OpenAI API Key
spring.ai.openai.api-key=YOUR_API_KEY_HERE
```

---

### 3. Build and Run the Project

Use the following command to run the tests:

```bash
# Run the tests
./mvnw test
```
