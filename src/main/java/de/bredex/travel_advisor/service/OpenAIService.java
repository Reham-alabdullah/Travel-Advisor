package de.bredex.travel_advisor.service;

import de.bredex.travel_advisor.domain.TravelSuggestion;
import de.bredex.travel_advisor.tool.DateTimeTools;
import de.bredex.travel_advisor.tool.TravelStyleTool;
import de.bredex.travel_advisor.tool.UserPreferencesTools;
import de.bredex.travel_advisor.tool.WebSearchTool;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.content.Media;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.Speech;
import org.springframework.ai.openai.audio.speech.SpeechModel;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {
    private final ChatClient chatClient;
    private final ImageModel imageModel;

    public OpenAIService(ChatClient.Builder builder, ImageModel imageModel) {
        this.chatClient = builder
                .defaultSystem("You're a helpful travel assistant.")
                .build();
        this.imageModel = imageModel;
    }

    public TravelSuggestion generateText(String prompt) {
        return this.chatClient.prompt(prompt)
                .tools(
                        new DateTimeTools(),
                        new UserPreferencesTools(),
                        new TravelStyleTool(),
                        new WebSearchTool()
                )
                .call()
                .entity(TravelSuggestion.class);
    }

    public Image generateImage(String prompt) {
        return this.imageModel.call(new ImagePrompt(prompt,
                OpenAiImageOptions.builder()
                        .quality("hd")
                        .N(1)
                        .height(1024)
                        .width(1024)
                        .build()
                )
        ).getResult().getOutput();
    }

}
