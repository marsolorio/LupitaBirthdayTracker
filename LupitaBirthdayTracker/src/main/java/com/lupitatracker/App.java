package com.lupitatracker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    // HashMap to store the names and birthdays
    private static HashMap<String, String> birthdayMap = new HashMap<>();

    public static void main(String[] args) {
        // Load the birthdays from the JSON file
        loadBirthdays();

        // Simple user input to look up birthdays
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a name to find their birthday: ");
        String name = scanner.nextLine();

        // Check if the name is in the HashMap
        if (birthdayMap.containsKey(name)) {
            System.out.println(name + "'s birthday is: " + birthdayMap.get(name));
        } else {
            System.out.println("Birthday for " + name + " is unknown.");
        }
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
}
