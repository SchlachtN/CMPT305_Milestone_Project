package ca.macewan.cmpt305.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
     * @param filePath String representing name of file to read
     * @throws IOException Unable to read or locate file
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
     * @param selectedAssessments Arraylist of PropertyAssessment objects
     */
    public PropertyAssessments(ArrayList<PropertyAssessment> selectedAssessments) {
        this.propertyAssessments = selectedAssessments;
    }

    /**
     * Getter for a specific property
     * @param id Account number of property to get
     * @return Existing property belonging to id or null if property is not found
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
     * @param propertyAssessment New PropertyAssessment object
     */
    public void addProperty(PropertyAssessment propertyAssessment) {
        this.propertyAssessments.add(propertyAssessment);
    }

    /**
     * Fetch the number of properties in the PropertyAssessments object
     * @return Integer representing the size of list
     */
    public Integer getSize() {
        return this.propertyAssessments.size();
    }

    /**
     * Find the property with the smallest assessment value and return the value
     * @return Integer representing the smallest assessment value
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
     * @return Integer representing the largest assessment value
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
     * @return Integer mean value of all assessment values in PropertyAssessments
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
     * @return Integer assessment value of PropertyAssessment object at middle index of sorted assessment values
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

    /**
     * Count the number of properties below the median of PropertyAssessments.
     * @return Integer count of values below the median
     */
    public Integer belowMedian() {
        int median = this.getMedian();
        int count = 0;
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            int propertyValue = propertyAssessment.getAssessmentValueInt();
            if (propertyValue <= median) {
                count++;
            }
        }
        return count;
    }

    /**
     * Count the number of properties below the mean of PropertyAssessments.
     * @return Integer count of values below the mean
     */
    public Integer belowMean() {
        int mean = this.getMean();
        int count = 0;
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            int propertyValue = propertyAssessment.getAssessmentValueInt();
            if (propertyValue <= mean) {
                count++;
            }
        }
        return count;
    }

    /**
     * Locate PropertyAssessment objects belonging to a specific neighbourhood
     * value and add to a new PropertyAssessments object.
     * Warning: This method has been replaced by the more general
     *          .filter(Predicate<PropertyAssessment> p) method.
     * @param neighbourhoodName String representing neighbourhood name to filter by
     * @return New PropertyAssessments object populated with PropertyAssessment
     * objects containing neighbourhood name matching the parameter value
     */
    @Deprecated
    public PropertyAssessments filterNeighbourhood(String neighbourhoodName) {
        ArrayList<PropertyAssessment> neighbourhoodAssessments = new ArrayList<>();
        // Access each property and if neighbourhood name matches pattern, add to list
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.isNeighbourhood(neighbourhoodName)) {
                neighbourhoodAssessments.add(propertyAssessment);
            }
        }
        // Return new PropertyAssessments with array list as argument
        return new PropertyAssessments(neighbourhoodAssessments);
    }

    /**
     * Locate PropertyAssessment objects belonging to a specific class
     * value and add to a new PropertyAssessments object.
     * Warning: This method has been replaced by the more general
     *          .filter(Predicate<PropertyAssessment> p) method.
     * @param className String representing assessment class to filter by
     * @return New PropertyAssessments object populated with PropertyAssessment
     * objects containing assessment class name matching the parameter value
     */
    @Deprecated
    public PropertyAssessments filterClass(String className) {
        ArrayList<PropertyAssessment> classAssessments = new ArrayList<>();
        // Access each property, match assessment class with parameter, and
        // determine whether to add to array list or not
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.isClass(className)) {
                classAssessments.add(propertyAssessment);
            }
        }
        // Return new PropertyAssessments with array list as argument
        return new PropertyAssessments(classAssessments);
    }

    /**
     * General filter to provide PropertyAssessments object containing only
     * PropertyAssessment objects with the filtered value
     * @param p Predicate for a PropertyAssessment object testing a provided
     *          lambda statement.
     * @return New PropertyAssessments object containing only PropertyAssessment
     * objects with filtered value
     */
    public PropertyAssessments filter(Predicate<PropertyAssessment> p) {
        // Create empty array list to add PropertyAssessment objects to
        ArrayList<PropertyAssessment> targetAssessments = new ArrayList<>();
        // Access each property and test it with the predicate. If test is true,
        // add to above array list
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (p.test(propertyAssessment)) {
                targetAssessments.add(propertyAssessment);
            }
        }
        return new PropertyAssessments(targetAssessments);
    }

    /**
     * Find all unique neighbourhood names in data
     * @return Hashset of neighbourhood names
     */
    public HashSet<String> getAllNeighbourhoods() {
        HashSet<String> neighbourhoods = new HashSet<>();

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            neighbourhoods.add(propertyAssessment.getNeighbourhoodName());
        }
        return neighbourhoods;
    }

    /**
     *
     * @return Simple string identifying object as PropertyAssessment object with size
     */
    @Override
    public String toString() {
        return "[PropertyAssessments:" + this.getSize() + "]";
    }

    /**
     *
     * @param o Object to compare to PropertyAssessments object
     * @return Boolean if Object o is a PropertyAssessments class and has a matching has value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAssessments pa = (PropertyAssessments) o;
        return this.hashCode() == pa.hashCode();
    }

    /**
     *
     * @return Hashcode value for propertyAssessments array list
     */
    @Override
    public int hashCode() {
        return this.propertyAssessments.hashCode();
    }
}