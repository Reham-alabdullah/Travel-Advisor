package de.bredex.travel_advisor.tool;

import org.springframework.ai.tool.annotation.Tool;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeTools {

    @Tool(description = "Get the current time in the user's timezone")
    public String currentTime() {
        return LocalTime.now().toString();
    }

    @Tool(description = "Get the current date in the user's timezone")
    public String currentDate() {
        return LocalDate.now().toString();
    }
}
