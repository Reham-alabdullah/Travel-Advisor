package de.bredex.travel_advisor;

import de.bredex.travel_advisor.domain.UserPreferences;
import de.bredex.travel_advisor.service.OpenAIService;
import de.bredex.travel_advisor.service.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class SpringAiDemoApplication implements CommandLineRunner {

    @Autowired
    OpenAIService openAIService;

    public static void main(String[] args) {
        SpringApplication.run(SpringAiDemoApplication.class, args);

    }

    @Override
    public void run(String... args) {
        String userMessage = UserInputService.getUserInput("Hi! How can I help you plan your next trip?");
        suggestTrip(userMessage);
        while (true) {
            String followUp = UserInputService.getUserInput("Do you want to refine your travel itinerary? (yes/no)");
            if (followUp.equalsIgnoreCase("yes")) {
                userMessage = UserInputService.getUserInput("Please provide more details or preferences for your trip:");
                suggestTrip(userMessage);
            } else {
                break;
            }
        }

        System.out.println("Have a nice day! :)");

    }

    private void suggestTrip(String userMessage) {

        var answer = openAIService.generateText(
                String.format("Give exactly 1 travel activity suggestion. This is the info about the recommendation request: %s", userMessage.trim())
        );
        System.out.println("Here is your travel itinerary: ");
        System.out.println(answer.toString());
    }

}
