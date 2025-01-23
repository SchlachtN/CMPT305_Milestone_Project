import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;

/**
 * Class
 */
public class ReadCSV {
    public static void main(String[] args) {
        String csvFileName = "src/main/resources/Property_Assessment_Data_2024.csv";

        try {
            String[][] data = readData(csvFileName);
            dataMenu(data);
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

    /**
     * Display menu for user to select CSV data info.
     * @param data - 2D array containing data
     */
    public static void dataMenu(String[][] data) {
        boolean runMenu = true;
        System.out.println("Welcome to the City of Edmonton Property Assessment Data Menu!\n");
        String menu = menuDisplay();
        Scanner userSelection = new Scanner(System.in);
        while (runMenu) {
            System.out.println(menu);
            String choice = userSelection.nextLine();
            int choiceInt = checkValidInt(choice);
            if (choiceInt == -1) {
                System.out.println("Invalid input\n");
            }
            else if (choiceInt >= 1 && choiceInt <= 4) {
                handleChoice(choiceInt, data);
            }
            else if (choiceInt == 5) {
                runMenu = false;
            } else {
                System.out.println("Invalid choice\n");
            }
        }
    }

    public static String menuDisplay() {
        return """
                Please select an option:
                \t1) Number of records
                \t2) Lowest and highest assessed property values
                \t3) Number of wards
                \t4) List of property assessment classes
                \t5) Exit
                
                Option:""";
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
     * @param data - 2D array containing info
     */
    public static void handleChoice(int choice, String[][] data) {
        switch (choice) {
            case 1:
                recordsCount(data);
                break;
            case 2:
                highAndLowAssetValues(data);
                break;
            case 3:
                numberOfWards(data);
                break;
            case 4:
                listAssessmentClasses(data);
                break;
            default:
                break;
        }
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
            if (value < lowestValue && value > 0) {
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
        HashSet<String> wardSet = new HashSet<String>();

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
        HashSet<String> assetClasses = new HashSet<String>();

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
