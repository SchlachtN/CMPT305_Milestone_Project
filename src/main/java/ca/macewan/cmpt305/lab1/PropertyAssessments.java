package ca.macewan.cmpt305.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class PropertyAssessments {
    private ArrayList<PropertyAssessment> propertyAssessments;

    public PropertyAssessments() {
        this.propertyAssessments = new ArrayList<>();
    }

    public PropertyAssessments(String filePath) throws IOException {
        // Establish propertyAssessments as a new, empty ArrayList
        this.propertyAssessments = new ArrayList<>();
        // Create a stream to read the CSV file
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            // Skip the header - this assumes the first line is a header
            reader.readLine();
            // Read the file line by line and store all rows into an ArrayList
            String line;
            while ((line = reader.readLine()) != null) {
                // Split a line by comma works for simple CSV files
                String[] values = line.split(",");
                // Create new ca.macewan.cmpt305.lab1.PropertyAssessment class with string array
                PropertyAssessment p = new PropertyAssessment(values);
                // Add new property to propertyAssessments
                this.propertyAssessments.add(p);
            }
        }
    }

    public PropertyAssessments(ArrayList<PropertyAssessment> selectedAssessments) {
        this.propertyAssessments = selectedAssessments;
    }

    public PropertyAssessment getPropertyAssessment(String id) {
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.getAccountNo().equals(id)) {
                return propertyAssessment;
            }
        }
        return null;
    }

    public void addProperty(PropertyAssessment propertyAssessment) {
        this.propertyAssessments.add(propertyAssessment);
    }

    public Integer getSize() {
        return this.propertyAssessments.size();
    }

    public Integer findMinimumValue() {
        int min = Integer.MAX_VALUE;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.getAssessmentValueInt() == null) {
                continue;
            }
            if (propertyAssessment.getAssessmentValueInt() < min) {
                min = propertyAssessment.getAssessmentValueInt();
            }
        }
        return min;
    }

    public Integer findMaximumValue() {
        int max = Integer.MIN_VALUE;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.getAssessmentValueInt() == null) {
                continue;
            }
            if (propertyAssessment.getAssessmentValueInt() > max) {
                max = propertyAssessment.getAssessmentValueInt();
            }
        }
        return max;
    }

    public Integer getRange() {
        int min = this.findMinimumValue();
        int max = this.findMaximumValue();
        return (max - min);
    }

    public Integer getMean() {
        BigInteger sum = new BigInteger("0");

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            BigInteger propertyValue = new BigInteger(propertyAssessment.getAssessmentValue());
            sum = sum.add(propertyValue);
        }

        String count = Integer.toString(propertyAssessments.size());
        BigInteger propertyCount = new BigInteger(count);
        return sum.divide(propertyCount).intValue();
    }

    public Integer getMedian() {
        int[] propertyValues = new int[this.propertyAssessments.size()];
        int index = 0;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
           propertyValues[index] = propertyAssessment.getAssessmentValueInt();
           index++;
        }

        Arrays.sort(propertyValues);
        if (propertyValues.length % 2 == 0) {
            return propertyValues[propertyValues.length / 2];
        }

        return (propertyValues[(propertyValues.length - 1) / 2] + propertyValues[propertyValues.length / 2]) / 2;
    }

    public PropertyAssessments getNeighbourhoodAssessments(String input) {
        String pattern = input.toUpperCase();
        PropertyAssessments neighbourhoodAssessments = new PropertyAssessments();

        for (PropertyAssessment propertyAssessment: propertyAssessments) {
            String neighbourhoodName = propertyAssessment.getNeighbourhoodName();
            if (pattern.equals(neighbourhoodName)) {
                neighbourhoodAssessments.addProperty(propertyAssessment);
            }
        }
        return neighbourhoodAssessments;
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

    // TODO - Ask Hanan if predicate should be moved into function and have method ask for pattern
    public PropertyAssessments filter(Predicate<PropertyAssessment> p) {
        ArrayList<PropertyAssessment> targetAssessments = new ArrayList<>();
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (p.test(propertyAssessment)) {
                targetAssessments.add(propertyAssessment);
            }
        }
        return new PropertyAssessments(targetAssessments);
    }
}