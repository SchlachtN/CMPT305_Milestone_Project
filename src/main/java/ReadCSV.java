import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * An example of how to read and process a simple CSV file.
 * This code uses only basic features of the Java language.
 * Feel free to modify this example by using a List (instead of array)
 * and other collections.
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
     * Print all rows of data.
     * @param data - 2D array containing data
     */
    public static void printData(String[][] data) {
        System.out.println("The number of records: " + data.length);
        for (String[] row : data) {
            System.out.println("Number of parameters: " + row.length);
            System.out.println("Account Number: " + row[0]);
            System.out.println("Suite: " + row[1]);
            System.out.println("House Number: " + row[2]);
            System.out.println("Street Name: " + row[3]);
            System.out.println("Garage: " + row[4]);
            System.out.println("Neighbourhood ID: " + row[5]);
            System.out.println("Neighbourhood: " + row[6]);
            System.out.println("Ward: " + row[7]);
            System.out.println("Assessed Value: $" + row[8]);
            System.out.println("Latitude: " + row[9]);
            System.out.println("Longitude: " + row[10]);
            System.out.println("Point Location: " + row[11]);
            System.out.println("Assessment Class % 1: " + row[12] + "%");
            System.out.println("Assessment Class % 2: " + row[13] + "%");
            System.out.println("Assessment Class % 3: " + row[14] + "%");
            System.out.println("Assessment Class 1: " + row[15]);
            if (row.length > 16) {
                System.out.println("Assessment Class 2: " + row[16]);
            }
            if (row.length > 17) {
                System.out.println("Assessment Class 3: " + row[17]);
            }
            System.out.println();
        }
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

        }
    }

    /**
     * Display number of records in the data set
     * @param data - 2D array containing nfo
     */
    public static void recordsCount(String[][] data) {
        System.out.println("The number of records: " + data.length + "\n");
    }

}
