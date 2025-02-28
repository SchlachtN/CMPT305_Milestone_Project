package ca.macewan.cmpt305.lab1;

public class PropertyAssessment {
    private final String accountNo;
    private PropertyAddress propertyAddress;
    private String garage;
    private Neighbourhood neighbourhood;
    private String assessmentValue;
    private final PointLocation pointLocation;
    private AssessmentClass assessmentClassification;

    /**
     * Constructor takes a specific String array containing 16-18 values
     * pertaining to property data.
     * @param propertyData String array containing property data
     */
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

    /**
     * Getter method for account number of PropertyAssessment object.
     * @return String value of account number
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Getter method for full address of PropertyAssessment object.
     * @return String value of Address object
     */
    public String getAddress() {
        return propertyAddress.toString();
    }

    /**
     * Getter method for integer assessment value of PropertyAssessment object.
     * @return Integer value of assessment value
     */
    public Integer getAssessmentValueInt() {
        if (assessmentValue.matches("[0-9]+")) {
            return Integer.parseInt(assessmentValue);
        } else {
            return null;
        }
    }

    /**
     * Getter method for String assessment value of PropertyAssessment object.
     * @return String value of assessment value
     */
    public String getAssessmentValue() {
        return assessmentValue;
    }

    /**
     * Getter method for full assessment class information of PropertyAssessment object.
     * @return String value of AssessmentClass object
     */
    public String getAssessmentClassification() {
        return assessmentClassification.toString();
    }

    /**
     * Getter method for full neighbourhood information of PropertyAssessment object.
     * @return String value of Neighbourhood object
     */
    public String getNeighbourhood() {
        return neighbourhood.toString();
    }

    /**
     * Getter method for name of neighbourhood value in PropertyAssessment object.
     * @return String value of neighbourhoodName value in Neighbourhood object
     */
    public String getNeighbourhoodName() {
        return neighbourhood.getNeighbourhoodName();
    }

    /**
     * Getter method for full geographic coordinate information in PropertyAssessment object.
     * @return String value of geographic coordinates in PointLocation object
     */
    public String getPointLocation() {
        return pointLocation.toString();
    }

    /**
     * Compare string parameter to object neighbourhoodName value.
     * Warning: Was only required in now depreciated filterNeighbourhood
     * method in PropertyAssessments. Will remain to allow functionality
     * of filterNeighbourhood method.
     * @param neighbourhood String value representing neighbourhood name
     * @return True if neighbourhood matches with the neighbourhoodName
     * value the neighbourhood object. Otherwise, return false.
     */
    @Deprecated
    public boolean isNeighbourhood(String neighbourhood) {
        neighbourhood = neighbourhood.toUpperCase();
        return getNeighbourhoodName().equals(neighbourhood);
    }

    /**
     * Checks if PropertyAssessment object belongs to a specified assessment class.
     * @param className String value representing name of an assessment class
     * @return True if className matches the name of any assessment class
     * in the AssessmentClass object. Otherwise, return false.
     */
    public boolean isClass(String className) {
        className = className.toUpperCase();
        if (assessmentClassification.getClassOne().equals(className)) {
            return true;
        } else if (assessmentClassification.getClassTwo().equals(className)) {
            return true;
        } else return assessmentClassification.getClassThree().equals(className);
    }

    /**
     *
     * @return String value containing account number and full address of PropertyAddress
     */
    @Override
    public String toString() {
        return this.accountNo + ": " + this.getAddress();
    }

    /**
     *
     * @param o Object to compare to PropertyAssessment class
     * @return True if o is a PropertyAssessment object and has the same unique
     * account number value. Otherwise, return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAssessment p = (PropertyAssessment) o;
        return accountNo.equals(p.accountNo);
    }

    /**
     *
     * @return Hashcode value of unique account number
     */
    @Override
    public int hashCode() {
        return this.accountNo.hashCode();
    }
}
