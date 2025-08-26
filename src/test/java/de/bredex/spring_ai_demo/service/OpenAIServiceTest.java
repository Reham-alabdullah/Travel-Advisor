package de.bredex.spring_ai_demo.service;

import de.bredex.travel_advisor.service.OpenAIService;
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
    @Disabled
    @Test
    void testCatRecognizer() {
        final Media image = new Media(MimeTypeUtils.IMAGE_JPEG, new ClassPathResource("image/cat.jpg"));
        final boolean containsCat = this.service.catRecognizer(image);
        System.out.println(containsCat ? "Image shows a cat" : "Image shows not a cat");
    }
    @Disabled
    @Test
    void testGenerateSpeech() {
        try {
            final String prompt = Files.readString(Path.of("src/test/resources/audio/speech.trans"));
            final Speech response = this.service.generateSpeech(prompt);
            Files.write(Path.of("src/test/resources/audio/speech.mp3"), response.getOutput(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Disabled
    @Test
    void testTranscribe() {
        final Resource audio = new FileSystemResource("src/test/resources/audio/speech.flac");
        final String response = this.service.transcribe(audio);
        System.out.printf("Audio transcription: %s", response);
    }
    @Disabled
    @Test
    void testGenerateEmbedding() {
        final float[] embedding = this.service.generateEmbedding("Hello AI");
        System.out.printf("Embedding of 'Hello AI': %s%n", Arrays.toString(embedding));
    }
}
