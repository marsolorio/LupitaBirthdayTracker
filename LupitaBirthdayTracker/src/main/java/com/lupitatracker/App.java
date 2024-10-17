package com.lupitatracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    // HashMap to store the names and birthdays
    private static HashMap<String, String> birthdayMap = new HashMap<>();

    public static void main(String[] args) {
        // Load the birthdays from the JSON file
        loadBirthdays();

        // Simple user input to look up birthdays
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a name or partial name to find their birthday(s): ");
        String name = scanner.nextLine();

        // Perform partial name matching
        searchBirthday(name);
    }

    // Method to load birthdays from a JSON file
    private static void loadBirthdays() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("birthday.json")) {
            Type mapType = new TypeToken<HashMap<String, String>>() {
            }.getType();
            birthdayMap = gson.fromJson(reader, mapType);
            System.out.println("Birthdays loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading birthday data: " + e.getMessage());
        }
    }

    // Method to search for birthdays using partial name matching
    private static void searchBirthday(String name) {
        boolean found = false;
        for (Map.Entry<String, String> entry : birthdayMap.entrySet()) {
            if (entry.getKey().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(entry.getKey() + "'s birthday is: " + entry.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No birthdays found for the name: " + name);
        }
    }
}
