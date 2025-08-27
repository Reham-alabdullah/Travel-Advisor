package de.bredex.travel_advisor.service;

import java.util.Scanner;

public class UserInputService {
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserInput(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
