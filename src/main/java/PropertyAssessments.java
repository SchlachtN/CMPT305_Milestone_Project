import java.util.ArrayList;

public class PropertyAssessments {
    private ArrayList<PropertyAssessment> propertyAssessments;

    public PropertyAssessments() {
        this.propertyAssessments = new ArrayList<>();
    }

    public void addProperty(PropertyAssessment propertyAssessment) {
        this.propertyAssessments.add(propertyAssessment);
    }
}
