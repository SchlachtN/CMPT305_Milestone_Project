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
        Scanner fileChoice = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String fileName = fileChoice.nextLine();
        String filePath = "src/main/resources/" + fileName;
        PropertyAssessments propertyAssessments = new PropertyAssessments();
        try {
            propertyAssessments = new PropertyAssessments(filePath);
            dataMenu(propertyAssessments);
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
            System.out.print(menu);
            String choice = userSelection.nextLine();
            int choiceInt = checkValidInt(choice);
            if (choiceInt == -1) {
                System.out.println("Invalid input");
            }
            else if (choiceInt >= 1 && choiceInt <= 3) {
                handleChoice(choiceInt, propertyAssessments);
            }
            else if (choiceInt == 4) {
                runMenu = false;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

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
        try {
            int choiceInt = Integer.parseInt(choice);
            if (choiceInt == -1) {
                return 0;
            } else {
                return choiceInt;
            }
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
            case 1:
                System.out.println("\nDescriptive statistics of all property assessments");
                generalStats(propertyAssessments);
                break;
            case 2:
                propertyInfo(propertyAssessments);
                break;
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
        int n = propertyAssessments.getSize();
        System.out.println("n = " + n);
        int min = propertyAssessments.findMinimumValue();
        System.out.println("min = " + currencyFormat(min));
        int max = propertyAssessments.findMaximumValue();
        System.out.println("max = " + currencyFormat(max));
        int range = propertyAssessments.getRange();
        System.out.println("range = " + currencyFormat(range));
        int mean = propertyAssessments.getMean();
        System.out.println("mean = " + currencyFormat(mean));
        int median = propertyAssessments.getMedian();
        System.out.println("median = " + currencyFormat(median));
    }

    /**
     * Call various methods in the PropertyAssessment class to display property specific information
     * @param propertyAssessments - PropertyAssessments object containing list of PropertyAssessment objects
     */
    public static void propertyInfo(PropertyAssessments propertyAssessments) {
        Scanner accountInput = new Scanner(System.in);
        System.out.print("\nFind a property assessment by account number: ");
        String accountNo = accountInput.nextLine();
        PropertyAssessment searchedAccount = propertyAssessments.getPropertyAssessment(accountNo);
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

    public static void neighbourhoodStats(PropertyAssessments propertyAssessments) {
        Scanner neighbourhoodInput = new Scanner(System.in);
        System.out.print("\nNeighbourhood: ");
        String searchNeighbourhood = neighbourhoodInput.nextLine();
        PropertyAssessments neighbourhoodAssessments = propertyAssessments.getNeighbourhoodAssessments(searchNeighbourhood);
        if (neighbourhoodAssessments.getSize() == 0) {
            System.out.println("Neighbourhood not found");
            return;
        }
        System.out.println("Statistics (neighbourhood = " + searchNeighbourhood + ")");
        generalStats(neighbourhoodAssessments);
    }
}
