package de.bredex.travel_advisor.service;

import de.bredex.travel_advisor.tool.DateTimeTools;
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
    private final SpeechModel speechModel;
    private final OpenAiAudioTranscriptionModel transcriptionModel;
    private final EmbeddingModel embeddingModel;

    public OpenAIService(ChatClient.Builder builder, ImageModel imageModel, SpeechModel speechModel, OpenAiAudioTranscriptionModel transcriptionModel, EmbeddingModel embeddingModel) {
        this.chatClient = builder.build();
        this.imageModel = imageModel;
        this.speechModel = speechModel;
        this.transcriptionModel = transcriptionModel;
        this.embeddingModel = embeddingModel;
    }

    public String generateText(String prompt) {
        return this.chatClient.prompt(prompt)
                .tools(new DateTimeTools())
                .call()
                .content();
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

    public boolean catRecognizer(Media image) {
        final String response = this.chatClient.prompt()
                .user(userSpec -> userSpec
                        .text("Decide whether or not there is a cat in the image. If there is a cat in the image return 'true', otherwise 'false'")
                        .media(image))
                .call()
                .content();
        return Boolean.parseBoolean(response);
    }

    public Speech generateSpeech(String prompt) {
        final OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .speed(1.0f)
                .model(OpenAiAudioApi.TtsModel.TTS_1.value)
                .build();
        final SpeechPrompt speechPrompt = new SpeechPrompt(prompt, speechOptions);
        return this.speechModel.call(speechPrompt).getResult();
    }

    public String transcribe(Resource audio) {
        final OpenAiAudioTranscriptionOptions transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .language("en")
                .temperature(0f)
                .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .build();
        final AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audio, transcriptionOptions);
        return this.transcriptionModel.call(transcriptionRequest).getResult().getOutput();
    }

    public float[] generateEmbedding(String text) {
        return this.embeddingModel.embed(text);
    }
}
