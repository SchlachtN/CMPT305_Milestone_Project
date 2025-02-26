package ca.macewan.cmpt305.lab1;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class reads a CSV file ( specifically the CSV file for
 * property assessment values in the City of Edmonton) and
 * allow various methods to display specific information.
 */
public class Lab2Main {
    public static void main(String[] args) {
        // Prompt user for file name (in resources directory)
        Scanner fileChoice = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String fileName = fileChoice.nextLine();
        String filePath = "src/main/resources/" + fileName;
        PropertyAssessments propertyAssessments;
        // Attempt to create PropertyAssessments from file and run menu
        try {
            propertyAssessments = new PropertyAssessments(filePath);
            dataMenu(propertyAssessments);
        // Error location or reading file
        } catch (IOException e) {
            System.err.println("Error: can't open file: " + fileName);
        }

    }

    /**
     * Display menu for user to select CSV data info.
     * @param propertyAssessments - PropertyAssessments class that stores PropertyAssessments
     */
    public static void dataMenu(PropertyAssessments propertyAssessments) {
        boolean runMenu = true;
        String menu = menuDisplay();
        Scanner userSelection = new Scanner(System.in);
        while (runMenu) {
            // Display menu and ask user for option selection
            System.out.print(menu);
            String choice = userSelection.nextLine();
            int choiceInt = checkValidInt(choice);
            // User did not enter a number
            if (choiceInt == -1) {
                System.out.println("Invalid input");
            } else if (choiceInt >= 1 && choiceInt <= 3) { // Options 1-3
                handleChoice(choiceInt, propertyAssessments);
            } else if (choiceInt == 4) { // Select to close menu
                runMenu = false;
            } else { // Number option not on menu
                System.out.println("Invalid choice");
            }
        }
    }

    /**
     * @return multi-line string for menu to display
     */
    public static String menuDisplay() {
        return """
                \nPlease select an option:
                \t1) General Statistics
                \t2) Individual Property Information
                \t3) Neighbourhood Information
                \t4) Exit
               
                Option:\s""";
    }

    /**
     * Check that user has inputted a valid integer for menu
     * @param choice - the string input given by the user
     * @return choiceInt - Integer conversion of user choice or -1 if unable to parse to int
     */
    public static int checkValidInt(String choice) {
        // Attempt to parse menu option and return
        try {
            return Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Display data for the user depending on choice selection
     * @param choice - int menu choice by user
     * @param propertyAssessments - PropertyAssessments object containing PropertyAssessment objects
     */
    public static void handleChoice(int choice, PropertyAssessments propertyAssessments) {
        switch (choice) {
            // General stats
            case 1:
                System.out.println("\nDescriptive statistics of all property assessments");
                generalStats(propertyAssessments);
                break;
            // Account info
            case 2:
                propertyInfo(propertyAssessments);
                break;
            // Neighbourhood info
            case 3:
                neighbourhoodStats(propertyAssessments);
                break;
            default:
                break;
        }
    }

    /**
     * Take an integer input and convert to string before formatting to display as Canadian currency
     * @param amount - Integer dollar value to format
     * @return Integer value converted to String and formatted in currency form
     */
    public static String currencyFormat(int amount) {
        String amountStr = Integer.toString(amount);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        BigDecimal assessmentValueBigDecimal = new BigDecimal(amountStr);
        return dollarFormat.format(assessmentValueBigDecimal);
    }

    /**
     * Call various methods in the PropertyAssessments class to give general information
     * @param propertyAssessments - PropertyAssessments object containing list of PropertyAssessment objects
     */
    public static void generalStats(PropertyAssessments propertyAssessments) {
        System.out.println("n = " + propertyAssessments.getSize());
        System.out.println("min = " + currencyFormat(propertyAssessments.findMinimumValue()));
        System.out.println("max = " + currencyFormat(propertyAssessments.findMaximumValue()));
        System.out.println("range = " + currencyFormat(propertyAssessments.getRange()));
        System.out.println("mean = " + currencyFormat(propertyAssessments.getMean()));
        System.out.println("median = " + currencyFormat(propertyAssessments.getMedian()));
    }

    /**
     * Call various methods in the PropertyAssessment class to display property specific information
     * @param propertyAssessments - PropertyAssessments object containing list of PropertyAssessment objects
     */
    public static void propertyInfo(PropertyAssessments propertyAssessments) {
        // Prompt user for account number of property
        Scanner accountInput = new Scanner(System.in);
        System.out.print("\nFind a property assessment by account number: ");
        String accountNo = accountInput.nextLine();
        PropertyAssessment searchedAccount = propertyAssessments.getPropertyAssessment(accountNo);
        // Account number cannot be found
        if (searchedAccount == null) {
            System.out.println("Error: Invalid account number...");
            return;
        }
        System.out.println("Account Number = " + searchedAccount.getAccountNo());
        System.out.println("Address = " + searchedAccount.getAddress());
        System.out.println("Assessed value = " + currencyFormat(searchedAccount.getAssessmentValueInt()));
        System.out.println("Assessment class = " + searchedAccount.getAssessmentClassification());
        System.out.println("Neighbourhood = " + searchedAccount.getNeighbourhood());
        System.out.println("Location = " + searchedAccount.getPointLocation());
    }

    /**
     * Filter properties by a specific neighbourhood and display general information
     * @param propertyAssessments PropertyAssessments object containing list of PropertyAssessment classes
     */
    public static void neighbourhoodStats(PropertyAssessments propertyAssessments) {
        // Prompt user for neighbourhood name
        Scanner neighbourhoodInput = new Scanner(System.in);
        System.out.print("\nNeighbourhood: ");
        String searchNeighbourhood = neighbourhoodInput.nextLine();
        // TODO - Replace getNeigbourhoodAssessments with filter
        PropertyAssessments neighbourhoodAssessments = propertyAssessments.getNeighbourhoodAssessments(searchNeighbourhood);
        // Neighbourhood name not found
        if (neighbourhoodAssessments.getSize() == 0) {
            System.out.println("Neighbourhood not found");
            return;
        }
        System.out.println("Statistics (neighbourhood = " + searchNeighbourhood + ")");
        generalStats(neighbourhoodAssessments);
    }
}
