package ca.macewan.cmpt305.lab1;

import java.util.Scanner;

/**
 * Handles user input and display menu options and requested data
 */
public class Menu {
    private PropertyAssessments propertyAssessments;

    // Menu must pass a PropertyAssessments object as a parameter.
    public Menu(PropertyAssessments givenPropertyAssessments) {
        this.propertyAssessments = givenPropertyAssessments;
    }

    /**
     * Determine if user has selected a valid menu option and take the
     * appropriate option.
     */
    public void runMenu() {
        boolean keepMenu = true;
        String menu = menuDisplay();
        Scanner userSelection = new Scanner(System.in);
        while (keepMenu) {
            // Display menu and ask for user to select option
            System.out.print(menu);
            String choice = userSelection.nextLine();
            int choiceInt = checkValidInt(choice);
            // User entered a non-numerical option
            if (choiceInt == -1) {
                System.out.println("Invalid input");
            }
            // Valid options to fetch data
            else if (choiceInt >= 1 && choiceInt <= 2) {
                handleChoice(choiceInt, propertyAssessments);
            }
            // Quit menu and terminate
            else if (choiceInt == 3) {
                keepMenu = false;
            // Option number not on menu
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    /**
     * @return multi-line string representing menu
     */
    private String menuDisplay() {
        return """
                \nPlease select an option:
                \t1) Neighbourhood Information
                \t2) Class information
                \t3) Exit
               
                Option:\s""";
    }

    /**
     * Check that user has inputted a valid integer for menu
     * @param choice - the string input given by the user
     * @return choiceInt - Integer conversion of user choice or -1 if unable to parse to int
     */
    private int checkValidInt(String choice) {
        // Attempt to parse to int
        try {
            return Integer.parseInt(choice);
        // Return -1 indicating parse failed
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Handle user menu selection to display appropriate data
     * @param choice - int menu choice by user
     * @param propertyAssessments - PropertyAssessment object containing all PropertyAssessment objects in CSV file
     */
    private void handleChoice(int choice, PropertyAssessments propertyAssessments) {
        switch (choice) {
            // Information for specific neighbourhood
            case 1:
                neighbourhoodInfo(propertyAssessments);
                break;
            // Information for specific assessment class
            case 2:
                assessmentClassInfo(propertyAssessments);
                break;
            default:
                break;
        }
    }

    /**
     * Create filtered PropertyAssessments object for specific neighbourhood
     * and displays relevant information for neighbourhood properties
     * @param propertyAssessments - PropertyAssessment object containing all PropertyAssessment objects in CSV file
     */
    private void neighbourhoodInfo(PropertyAssessments propertyAssessments) {
        // Ask user for neighbourhood name
        Scanner neighbourhoodInput = new Scanner(System.in);
        System.out.print("\nPlease enter a neighbourhood name: ");
        String neighbourhoodName = neighbourhoodInput.nextLine();
        // Create new PropertyAssessments with filtered neighbourhood
        PropertyAssessments neighbourhoodAssessments = propertyAssessments.filter((property) -> neighbourhoodName.toUpperCase().equals(property.getNeighbourhoodName()));
        // In case neighbourhood name is not found or does not exist
        if (neighbourhoodAssessments.getSize() == 0) {
            System.out.println("Sorry, can't find data in " + neighbourhoodName);
            return;
        }
        System.out.println("There are " + String.format("%,d", neighbourhoodAssessments.getSize()) + " properties in " + neighbourhoodName);
        System.out.println("The mean value is CAD " + String.format("%,d", neighbourhoodAssessments.getMean()));
        System.out.println("The median value is CAD " + String.format("%,d", neighbourhoodAssessments.getMedian()));
    }

    /**
     * Create filtered PropertyAssessments class for specific assessment class
     * and display relevant information for properties belonging to assessment
     * class
     * @param propertyAssessments - PropertyAssessment object containing all PropertyAssessment objects in CSV file
     */
    private void assessmentClassInfo(PropertyAssessments propertyAssessments) {
        // Ask user for assessment class name
        Scanner classInput = new Scanner(System.in);
        System.out.print("\nPlease enter an assessment class: ");
        String className = classInput.nextLine();
        // Create new PropertyAssessments object with filtered assessment classes
        PropertyAssessments classAssessments = propertyAssessments.filter((property) -> property.isClass(className.toUpperCase()));
        // In case assessment class is not found or does not exist
        if (classAssessments.getSize() == 0) {
            System.out.println("Sorry, can't find " + className + " properties");
            return;
        }
        System.out.println("There are " + String.format("%,d", classAssessments.getSize()) + " " + className + " properties in Edmonton");
        System.out.println("The min value is CAD " + String.format("%,d", classAssessments.findMinimumValue()));
        System.out.println("The max value is CAD " + String.format("%,d", classAssessments.findMaximumValue()));
    }
}
