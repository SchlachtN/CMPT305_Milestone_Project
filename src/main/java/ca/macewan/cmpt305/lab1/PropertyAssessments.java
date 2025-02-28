package ca.macewan.cmpt305.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Serves as a list for PropertyAssessment objects.
 * Methods derive specific information namely for PropertyAssessment values
 */
public class PropertyAssessments {
    private ArrayList<PropertyAssessment> propertyAssessments;

    /**
     * Constructor that takes no arguments and creates an empty ArrayList
     */
    public PropertyAssessments() {
        this.propertyAssessments = new ArrayList<>();
    }

    /**
     * Constructor takes a string filename and attempts to read file, generate
     * PropertyAssessment objects from data, and populate in ArrayList.
     * @param filePath - String representing name of file to read
     * @throws IOException - Unable to read or locate file
     */
    public PropertyAssessments(String filePath) throws IOException {
        // Establish propertyAssessments as a new, empty ArrayList
        this.propertyAssessments = new ArrayList<>();
        // Create a stream to read the CSV file
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            // Skip header
            reader.readLine();
            // Read line by line
            String line;
            while ((line = reader.readLine()) != null) {
                // Split a line by comma works for simple CSV files
                String[] values = line.split(",");
                // Create new PropertyAssessment class with values String array
                PropertyAssessment p = new PropertyAssessment(values);
                // Add new property to propertyAssessments
                this.propertyAssessments.add(p);
            }
        }
    }

    /**
     * Constructor creates from existing ArrayList of PropertyAssessment objects
     * @param selectedAssessments - Arraylist of PropertyAssessment objects
     */
    public PropertyAssessments(ArrayList<PropertyAssessment> selectedAssessments) {
        this.propertyAssessments = selectedAssessments;
    }

    /**
     * Getter for a specific property
     * @param id - Account number of property to get
     * @return - Existing property belonging to id or null if property is not found
     */
    public PropertyAssessment getPropertyAssessment(String id) {
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.getAccountNo().equals(id)) {
                return propertyAssessment;
            }
        }
        return null;
    }

    /**
     * Adder for new PropertyAssessment object into list
     * @param propertyAssessment - New PropertyAssessment object
     */
    public void addProperty(PropertyAssessment propertyAssessment) {
        this.propertyAssessments.add(propertyAssessment);
    }

    /**
     * Fetch the number of properties in the PropertyAssessments object
     * @return - Integer representing the size of list
     */
    public Integer getSize() {
        return this.propertyAssessments.size();
    }

    /**
     * Find the property with the smallest assessment value and return the value
     * @return - Integer representing the smallest assessment value
     */
    public Integer findMinimumValue() {
        // Initial largest possible value to compare to
        int min = Integer.MAX_VALUE;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            // Assessment value of property is blank or null, skip
            if (propertyAssessment.getAssessmentValueInt() == null) {
                continue;
            }
            // Compare if smaller than the current minimum value
            if (propertyAssessment.getAssessmentValueInt() < min) {
                min = propertyAssessment.getAssessmentValueInt();
            }
        }
        return min;
    }

    /**
     * Find the property with the largest assessment value and return the value
     * @return - Integer representing the largest assessment value
     */
    public Integer findMaximumValue() {
        // Initial smallest possible value to compare to
        int max = Integer.MIN_VALUE;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            // Assessment value of property is blank or null, skip
            if (propertyAssessment.getAssessmentValueInt() == null) {
                continue;
            }
            // Compare if larger than the current maximum value
            if (propertyAssessment.getAssessmentValueInt() > max) {
                max = propertyAssessment.getAssessmentValueInt();
            }
        }
        return max;
    }

    /**
     * Find range between maximum and minimum assessment values
     * @return Integer difference between maximum and minimum assessment values
     */
    public Integer getRange() {
        int min = this.findMinimumValue();
        int max = this.findMaximumValue();
        return (max - min);
    }

    /**
     * Find the average assessment value for all properties
     * @return - Integer mean value of all assessment values in PropertyAssessments
     */
    public Integer getMean() {
        BigInteger sum = new BigInteger("0");

        // Add assessment value of each property to sum
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            BigInteger propertyValue = new BigInteger(propertyAssessment.getAssessmentValue());
            sum = sum.add(propertyValue);
        }

        // Get number of PropertyAssessment objects
        String count = this.getSize().toString();
        BigInteger propertyCount = new BigInteger(count);
        // Calculate and return mean
        return sum.divide(propertyCount).intValue();
    }

    /**
     * Calculate the median "middle" assessment value of all PropertyAssessment objects
     * @return - Integer assessment value of PropertyAssessment object at middle index of sorted assessment values
     */
    public Integer getMedian() {
        // Create an empty array to store all assessment values
        int[] propertyValues = new int[this.getSize()];
        int index = 0;

        // Add all assessment values to array
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
           propertyValues[index] = propertyAssessment.getAssessmentValueInt();
           index++;
        }

        // Sort the assessment values in ascending order
        Arrays.sort(propertyValues);
        // If array size is odd, return assessment value at middle index
        if (propertyValues.length % 2 != 0) {
            return propertyValues[propertyValues.length / 2];
        }

        // If array size is even, add the two assessment values closest to the middle and divide sum by 2
        return (propertyValues[(propertyValues.length - 1) / 2] + propertyValues[propertyValues.length / 2]) / 2;
    }

    @Deprecated
    public PropertyAssessments filterNeighbourhood(String neighbourhoodName) {
        ArrayList<PropertyAssessment> neighbourhoodAssessments = new ArrayList<>();
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.isNeighbourhood(neighbourhoodName)) {
                neighbourhoodAssessments.add(propertyAssessment);
            }
        }
        return new PropertyAssessments(neighbourhoodAssessments);
    }

    @Deprecated
    public PropertyAssessments filterClass(String className) {
        ArrayList<PropertyAssessment> classAssessments = new ArrayList<>();
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.isClass(className)) {
                classAssessments.add(propertyAssessment);
            }
        }
        return new PropertyAssessments(classAssessments);
    }

    public PropertyAssessments filter(Predicate<PropertyAssessment> p) {
        ArrayList<PropertyAssessment> targetAssessments = new ArrayList<>();
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (p.test(propertyAssessment)) {
                targetAssessments.add(propertyAssessment);
            }
        }
        return new PropertyAssessments(targetAssessments);
    }

    @Override
    public String toString() {
        return "[PropertyAssessments:" + this.getSize() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAssessments pa = (PropertyAssessments) o;
        return this.hashCode() == pa.hashCode();
    }

    @Override
    public int hashCode() {
        return this.propertyAssessments.hashCode();
    }
}