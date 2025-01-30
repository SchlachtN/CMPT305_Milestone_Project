import java.util.ArrayList;

public class PropertyAssessments {
    private ArrayList<PropertyAssessment> propertyAssessments;

    public PropertyAssessments() {
        this.propertyAssessments = new ArrayList<>();
    }

    public PropertyAssessment getPropertyAssessment(int id) {
        String searchedAccountNo = Integer.toString(id);
        for (PropertyAssessment propertyAssessment : propertyAssessments) {
            if (propertyAssessment.getAccountNo().equals(searchedAccountNo)) {
                return propertyAssessment;
            }
        }
        return null;
    }

    public void addProperty(PropertyAssessment propertyAssessment) {
        this.propertyAssessments.add(propertyAssessment);
    }
}
