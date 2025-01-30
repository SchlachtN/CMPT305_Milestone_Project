import java.util.ArrayList;

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
            if (propertyAssessment.getAssessmentValue() == null) {
                continue;
            }
            if (propertyAssessment.getAssessmentValue() < min) {
                min = propertyAssessment.getAssessmentValue();
            }
        }
        return min;
    }

    public Integer findMaximumValue() {
        int max = Integer.MIN_VALUE;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.getAssessmentValue() == null) {
                continue;
            }
            if (propertyAssessment.getAssessmentValue() > max) {
                max = propertyAssessment.getAssessmentValue();
            }
        }
        return max;
    }

    public Integer getRange() {
        int min = this.findMinimumValue();
        int max = this.findMaximumValue();
        return (max - min);
    }
}