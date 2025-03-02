package ca.macewan.cmpt305.lab1;

/**
 * Stores and provides all information concerning the assessment classes of a
 * PropertyAssessment class.
 */
public class AssessmentClass {
    private String classOnePercentage;
    private String classTwoPercentage;
    private String classThreePercentage;
    private String classOne;
    private String classTwo;
    private String classThree;

    /**
     * Constructor for properties only belonging to class one
     * @param onePercentData Percentage information for class one
     * @param twoPercentData Percentage information for class two
     * @param threePercentData Percentage information for class three
     * @param classOneData Assessment class type for class one
     */
    public AssessmentClass(String onePercentData, String twoPercentData,
                           String threePercentData, String classOneData) {
        this.classOnePercentage = onePercentData;
        this.classTwoPercentage = twoPercentData;
        this.classThreePercentage = threePercentData;
        this.classOne = classOneData;
        this.classTwo = "";
        this.classThree = "";
    }

    /**
     * Constructor for properties with class one and class two information
     * @param onePercentData Percentage information for class one
     * @param twoPercentData Percentage information for class two
     * @param threePercentData Percentage information for class three
     * @param classOneData Assessment class type for class one
     * @param classTwoData Assessment class type for class two
     */
    public AssessmentClass(String onePercentData, String twoPercentData,
                String threePercentData, String classOneData,
                String classTwoData) {
            this.classOnePercentage = onePercentData;
            this.classTwoPercentage = twoPercentData;
            this.classThreePercentage = threePercentData;
            this.classOne = classOneData;
            this.classTwo = classTwoData;
            this.classThree = "";
    }

    /**
     * Constructor for properties with three assessment classes
     * @param onePercentData Percentage information for class one
     * @param twoPercentData Percentage information for class two
     * @param threePercentData Percentage information for class three
     * @param classOneData Assessment class type for class one
     * @param classTwoData Assessment class type for class two
     * @param classThreeData Assessment class type for class three
     */
    public AssessmentClass(String onePercentData, String twoPercentData,
                           String threePercentData, String classOneData,
                           String classTwoData, String classThreeData) {
        this.classOnePercentage = onePercentData;
        this.classTwoPercentage = twoPercentData;
        this.classThreePercentage = threePercentData;
        this.classOne = classOneData;
        this.classTwo = classTwoData;
        this.classThree = classThreeData;
    }

    /**
     * Getter method for assessment class one type
     * @return String type for class one
     */
    public String getClassOne() {
        return classOne;
    }

    /**
     * Getter method for assessment class two type
     * @return String type for class two
     */
    public String getClassTwo() {
        return classTwo;
    }

    /**
     * Getter method for assessment class three type
     * @return String type for class three
     */
    public String getClassThree() {
        return classThree;
    }

    /**
     *
     * @return String formatted to list all assessment class types if they exist
     */
    @Override
    public String toString() {
        String classification = "[" + this.classOne + " " + this.classOnePercentage + "%";
        // Has a class two type
        if(!this.classTwoPercentage.isBlank()) {
            classification = classification + ", " +this.classTwo + " " + this.classTwoPercentage + "%";
        }
        // Has a class three type
        if(!this.classThreePercentage.isBlank()) {
            classification = classification + ", " +this.classThree + " " + this.classThreePercentage + "%";
        }

        classification = classification + "]";
        return classification;
    }

    /**
     *
     * @param o Object to compare to AssessmentClass object
     * @return True if o is an AssessmentClass object and has the same hashcode.
     * Otherwise, return false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssessmentClass a = (AssessmentClass) o;
        return a.hashCode() == this.hashCode();
    }

    /**
     *
     * @return Hashcode value of formatted string produced by toString
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
