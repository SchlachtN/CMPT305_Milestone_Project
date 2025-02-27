package ca.macewan.cmpt305.lab1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class attempts to read a specified CSV file (name provided by user).
 * File must be in the resources directory in project. Use data to populate
 * a PropertyAssessments class and then display terminal menu with Menu class.
 */
public class Lab3Main {
    public static void main(String[] args) {
        // Ask user for filename
        Scanner fileChoice = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String fileName = fileChoice.nextLine();
        String filePath = "src/main/resources/" + fileName;
        PropertyAssessments propertyAssessments;
        // Attempt to populate propertyAssessments and run menu
        try {
            propertyAssessments = new PropertyAssessments(filePath);
            Menu propertyMenu = new Menu(propertyAssessments);
            propertyMenu.runMenu();
        // CSV file could not be found
        } catch (IOException e) {
            System.err.println("Error: can't open file: " + fileName);
        }
    }
}
