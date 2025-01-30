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

    public String findMinimumValue() {
        int min = Integer.MAX_VALUE;
        String accountNo = null;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.formatAssessmentValue() == null) {
                continue;
            }
            if (propertyAssessment.getAssessmentValue() < min) {
                min = propertyAssessment.getAssessmentValue();
                accountNo = propertyAssessment.getAccountNo();
            }
        }
        return accountNo;
    }

    public String findMaximumValue() {
        int max = Integer.MIN_VALUE;
        String accountNo = null;

        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.formatAssessmentValue() == null) {
                continue;
            }
            if (propertyAssessment.getAssessmentValue() > max) {
                max = propertyAssessment.getAssessmentValue();
                accountNo = propertyAssessment.getAccountNo();
            }
        }
        return accountNo;
    }
}
