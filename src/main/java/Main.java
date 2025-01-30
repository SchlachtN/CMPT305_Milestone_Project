import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class reads a CSV file ( specifically the CSV file for
 * property assessment values in the City of Edmonton) and
 * allow various methods to display specific information.
 */
public class Main {
    public static void main(String[] args) {
        Scanner fileChoice = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String fileName = fileChoice.nextLine();
        //String csvFileName = "src/main/resources/" + fileName;
        String csvFileName = "src/main/resources/Property_Assessment_Data_2024.csv";

        try {
            PropertyAssessments propertyAssessments = readData(csvFileName);
            dataMenu(propertyAssessments);
        } catch (IOException e) {
            System.out.println("Failed to read " + csvFileName);
        }
    }

    /**
     * Read the contents of a CSV file and return data as a 2D array of String.
     * @param csvFileName - the CSV file name
     * @return data - the values in the CSV file
     * @throws IOException - input/output error
     */

    public static PropertyAssessments readData(String csvFileName) throws IOException {
        // Create a stream to read the CSV file
        PropertyAssessments propertyAssessments = new PropertyAssessments();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFileName))) {
            // Skip the header - this assumes the first line is a header
            reader.readLine();

            // Read the file line by line and store all rows into a 2D array
            String line;
            while ((line = reader.readLine()) != null) {
                // Split a line by comma works for simple CSV files
                String[] values = line.split(",");
                // Create new PropertyAssessment class with string array
                PropertyAssessment p = new PropertyAssessment(values);
                // Add new property to propertyAssessments
                propertyAssessments.addProperty(p);
            }
        }

        return propertyAssessments;
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
                System.out.println("Invalid input\n");
            }
            else if (choiceInt >= 1 && choiceInt <= 3) {
                handleChoice(choiceInt, propertyAssessments);
            }
            else if (choiceInt == 4) {
                runMenu = false;
            } else {
                System.out.println("Invalid choice\n");
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
                generalStats(propertyAssessments);
                break;
            case 2:
                propertyInfo(propertyAssessments);
                break;
            case 3:

                break;
            default:
                break;
        }
    }

    public static String currencyFormat(int amount) {
        String amountStr = Integer.toString(amount);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        BigDecimal assessmentValueBigDecimal = new BigDecimal(amountStr);
        return dollarFormat.format(assessmentValueBigDecimal);
    }

    public static void generalStats(PropertyAssessments propertyAssessments) {
        System.out.println("\nDescriptive statistics of all property assessments");
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
    }
}
