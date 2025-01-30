import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;

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

    /*
    /**
     * Read the contents of a CSV file and return data as a 2D array of String.
     * @param csvFileName - the CSV file name
     * @return data - the values in the CSV file
     * @throws IOException - input/output error

    public static String[][] readData(String csvFileName) throws IOException {
        // Create a stream to read the CSV file
        String[][] data;
        int index = 0;
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFileName))) {
            // Skip the header - this assumes the first line is a header
            reader.readLine();

            // Create 2D array to store all rows of data as String
            int initialSize = 100;
            data = new String[initialSize][];

            // Read the file line by line and store all rows into a 2D array
            String line;
            while ((line = reader.readLine()) != null) {
                // Split a line by comma works for simple CSV files
                String[] values = line.split(",");

                // Check if the array is full
                if (index == data.length)
                    // Array is full, create and copy all values to a larger array
                    data = Arrays.copyOf(data, data.length * 2);

                data[index++] = values;
            }
        }

        // Remove empty rows in the array and return it
        return Arrays.copyOf(data, index);
    }
     */

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

                break;
            case 3:

                break;
            default:
                break;
        }
    }

    public static void generalStats(PropertyAssessments propertyAssessments) {
        System.out.println("\nDescriptive statistics of all property assessments");
        int n = propertyAssessments.getSize();
        System.out.println("n = " + n);
        String minimumAccountNo = propertyAssessments.findMinimumValue();
        PropertyAssessment minimumProperty = propertyAssessments.getPropertyAssessment(minimumAccountNo);
        String min = minimumProperty.formatAssessmentValue();
        System.out.println("min = " + min);
        String maximumAccountNo = propertyAssessments.findMaximumValue();
        PropertyAssessment maximumProperty = propertyAssessments.getPropertyAssessment(maximumAccountNo);
        String max = maximumProperty.formatAssessmentValue();
        System.out.println("max = " + max);
    }

    /**
     * Display number of records in the data set
     * @param data - 2D array containing property info
     */
    public static void recordsCount(String[][] data) {
        System.out.println("The number of records: " + data.length + "\n");
    }

    /**
     * Find indices for highest and lowest assessed properties and display their info
     * @param data - 2D array containing property info
     */
    public static void highAndLowAssetValues(String[][] data) {
        double highestValue = 0;
        double lowestValue = Double.POSITIVE_INFINITY;
        int highestIndex = 0;
        int lowestIndex = 0;

        for (int i = 0; i < data.length; i++) {
            double value = Double.parseDouble(data[i][8]);

            if (value > highestValue) {
                highestValue = value;
                highestIndex = i;
            }
            if (value < lowestValue) {
                lowestValue = value;
                lowestIndex = i;
            }
        }

        System.out.println("Highest Value: $" + data[highestIndex][8]);
        System.out.print("Account Number: " + data[highestIndex][0] + " at ");
        System.out.print(data[highestIndex][2] + " " + data[highestIndex][3] + "\n");
        System.out.println("Lowest Value: $" + data[lowestIndex][8]);
        System.out.print("Account Number: " + data[lowestIndex][0] + " at ");
        System.out.print(data[lowestIndex][2] + " " + data[lowestIndex][3] + "\n\n");
    }

    /**
     * Determine number of wards for city properties and display count
     * @param data - 2D array containing property info
     */
    public static void numberOfWards(String[][] data) {
        HashSet<String> wardSet = new HashSet<>();

        for (String[] row : data) {
            if(!row[7].isBlank()) {
                wardSet.add(row[7]);
            }
        }

        System.out.println("The number of wards: " + wardSet.size() + "\n");
    }

    /**
     * Adds all assessment classes to a hash set and list them
     * @param data - 2D array containing property info
     */
    public static void listAssessmentClasses(String[][] data) {
        HashSet<String> assetClasses = new HashSet<>();

        for (String[] row : data) {
            assetClasses.add(row[15]);
            if (row.length > 16) {
                assetClasses.add(row[16]);
            }
            if (row.length > 17) {
                assetClasses.add(row[17]);
            }
        }

        System.out.println("Assessment Classes:");
        for (String assetClass : assetClasses) {
            if (!assetClass.isBlank()) {
                System.out.println(assetClass);
            }
        }
        System.out.println("\n");
    }
}
