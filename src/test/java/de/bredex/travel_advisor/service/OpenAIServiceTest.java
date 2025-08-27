package de.bredex.travel_advisor.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ai.content.Media;
import org.springframework.ai.image.Image;
import org.springframework.ai.openai.audio.speech.Speech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

@SpringBootTest
class OpenAIServiceTest {
    @Autowired
    private OpenAIService service;

    @Disabled
    @Test
    void testGenerateText() {
        final String response = this.service.generateText("Hello");
        System.out.printf("AI: %s%n", response);
    }
    @Disabled
    @Test
    void testToolCalling() {
        final String response = this.service.generateText("What time is it?");
        System.out.printf("AI: %s%n", response);
    }
    @Disabled
    @Test
    void testGenerateImage() {
        final Image image = this.service.generateImage("A cat holding an ice cream");
        System.out.printf("Generated image of a cat: %s%n", image.getUrl());
    }
}
