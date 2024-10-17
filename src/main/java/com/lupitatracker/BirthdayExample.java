package com.lupitatracker;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class BirthdayExample {

  // This is a private and static hashmap to store the birthdays
  private static HashMap<String, String> birthdayMap = new HashMap<String, String>();

  // This code reads the JSON file
  public static JSONArray readJSONArrayFile(String fileName) {
    // JSON parser object to parse the read file
    JSONParser jsonParser = new JSONParser();

    JSONArray birthdayArr = null;

    // Read JSON file
    try (FileReader reader = new FileReader(fileName)) {
      Object obj = jsonParser.parse(reader);

      birthdayArr = (JSONArray) obj;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return birthdayArr;
  }

  // Initialize the HashMap by reading the data from the JSON file
  public static void initializeMap(final String pathToFile) {
    JSONArray jsonData = readJSONArrayFile(pathToFile);

    // Loop over the list
    String birthday, name;
    JSONObject obj;
    for (int i = 0; i < jsonData.size(); i++) {
      // Parse the object and pull out the name and birthday
      obj = (JSONObject) jsonData.get(i);
      birthday = (String) obj.get("birthday");
      name = (String) obj.get("name");

      // Add the name and birthday to the hashmap
      birthdayMap.put(name, birthday);
    }
  }

  public static void main(final String[] args) {
    // Path to the JSON file
    String pathToFile = "/Users/martinsolorio/Desktop/LupitaBirthdayTracker/birthday.json";

    // Initialize the hash map with data from the JSON file
    initializeMap(pathToFile);

    // Get user input from the keyboard
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a name or part of a name: ");
    String name = input.nextLine();

    // Search for full or partial name matches
    boolean found = false;
    for (Map.Entry<String, String> entry : birthdayMap.entrySet()) {
      if (entry.getKey().toLowerCase().contains(name.toLowerCase())) {
        System.out.println("Birthday of " + entry.getKey() + ": " + entry.getValue());
        found = true;
      }
    }

    // If no match is found, print a message
    if (!found) {
      System.out.println("No matches found for " + name);
    }

    // Close the scanner
    input.close();
  }
}
