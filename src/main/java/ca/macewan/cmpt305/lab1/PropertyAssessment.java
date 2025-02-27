package ca.macewan.cmpt305.lab1;

public class PropertyAssessment {
    private String accountNo;
    private PropertyAddress propertyAddress;
    private String garage;
    private Neighbourhood neighbourhood;
    private String assessmentValue;
    private PointLocation pointLocation;
    private AssessmentClass assessmentClassification;

    public PropertyAssessment(String[] propertyData) {
        this.accountNo = propertyData[0];
        this.propertyAddress = new PropertyAddress(propertyData[1], propertyData[2], propertyData[3]);
        this.garage = propertyData[4];
        this.neighbourhood = new Neighbourhood(propertyData[5],propertyData[6],propertyData[7]);
        this.assessmentValue = propertyData[8];
        this.pointLocation = new PointLocation(propertyData[9],propertyData[10]);
        if (propertyData.length == 16) {
            this.assessmentClassification = new AssessmentClass(propertyData[12], propertyData[13],
                    propertyData[14], propertyData[15]);
        } else if (propertyData.length == 17) {
            this.assessmentClassification = new AssessmentClass(propertyData[12], propertyData[13],
                    propertyData[14], propertyData[15], propertyData[16]);
        } else if (propertyData.length == 18) {
            this.assessmentClassification = new AssessmentClass(propertyData[12], propertyData[13],
                    propertyData[14], propertyData[15], propertyData[16], propertyData[17]);
        }
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAddress() {
        return propertyAddress.toString();
    }

    public Integer getAssessmentValueInt() {
        if (assessmentValue.matches("[0-9]+")) {
            return Integer.parseInt(assessmentValue);
        } else {
            return null;
        }
    }

    public String getAssessmentValue() {
        return assessmentValue;
    }

    public String getAssessmentClassification() {
        return assessmentClassification.toString();
    }

    public String getNeighbourhood() {
        return neighbourhood.toString();
    }

    public String getNeighbourhoodName() {
        return neighbourhood.getNeighbourhoodName();
    }

    public String getPointLocation() {
        return pointLocation.toString();
    }

    public boolean isNeighbourhood(String neighbourhood) {
        neighbourhood = neighbourhood.toUpperCase();
        return getNeighbourhoodName().equals(neighbourhood);
    }

    public boolean isClass(String className) {
        className = className.toUpperCase();
        if (assessmentClassification.getClassOne().equals(className)) {
            return true;
        } else if (assessmentClassification.getClassTwo().equals(className)) {
            return true;
        } else if (assessmentClassification.getClassThree().equals(className)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.accountNo + ": " + this.getAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAssessment p = (PropertyAssessment) o;
        return accountNo.equals(p.accountNo);
    }

    @Override
    public int hashCode() {
        return this.accountNo.hashCode();
    }
}
