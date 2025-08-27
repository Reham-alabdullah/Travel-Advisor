package de.bredex.travel_advisor.tool;

import de.bredex.travel_advisor.service.UserInputService;
import org.springframework.ai.tool.annotation.Tool;

import java.util.Scanner;

public class TravelStyleTool {

    @Tool(description = "Get the travel style of the user.")
    public String getTravelStyle() {
        return UserInputService.getUserInput("What is your travel style? (e.g., adventure, relaxation, cultural)");

    }
}
