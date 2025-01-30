import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class PropertyAssessments {
    private ArrayList<PropertyAssessment> propertyAssessments;

    public PropertyAssessments() {
        this.propertyAssessments = new ArrayList<>();
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
}