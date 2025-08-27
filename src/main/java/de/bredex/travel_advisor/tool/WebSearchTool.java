package de.bredex.travel_advisor.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class WebSearchTool {
    private static final String apiKey = "af21de540fbffd75ddae8c9840db3d3655aa6859";
    private static final String SERPER_ENDPOINT = "https://google.serper.dev/search";
    private final Logger logger = LoggerFactory.getLogger(WebSearchTool.class.getName());

    @Tool(description = "Perform a web search for the given query and return results.")
    public String searchWeb(String query){
        String requestBody = "{ \"q\": \"" + query + "\" }";
        var webClient = WebClient.builder()
                .baseUrl(SERPER_ENDPOINT)
                .defaultHeader("X-API-KEY", apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
        String response = webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(e -> Mono.just("{\"error\": \"" + e.getMessage() + "\"}"))
                .block();
        logger.debug(response);
        return response;

    }
}
