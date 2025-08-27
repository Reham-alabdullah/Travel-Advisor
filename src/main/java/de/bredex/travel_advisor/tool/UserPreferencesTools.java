package de.bredex.travel_advisor.tool;

import de.bredex.travel_advisor.service.UserInputService;
import org.springframework.ai.tool.annotation.Tool;

public class UserPreferencesTools {


    @Tool(description = "Get the preferred travel destination of the user.")
    public String getPreferredDestination() {
        return UserInputService.getUserInput("Tell me about your travel location preferences. What is your preferred destination?");
    }


    @Tool(description = "Get the budget of the user.")
    public String getBudget() {
        return UserInputService.getUserInput("What is your budget? (e.g., low, medium, high)");
    }

}
