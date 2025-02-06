public class AssessmentClass {
    private String classOnePercentage;
    private String classTwoPercentage;
    private String classThreePercentage;
    private String classOne;
    private String classTwo;
    private String classThree;

    public AssessmentClass(String onePercentData, String twoPercentData,
                           String threePercentData, String classOneData) {
        this.classOnePercentage = onePercentData;
        this.classTwoPercentage = twoPercentData;
        this.classThreePercentage = threePercentData;
        this.classOne = classOneData;
        this.classTwo = "";
        this.classThree = "";
    }

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

    public String getClassOne() {
        return classOne;
    }

    public String getClassTwo() {
        return classTwo;
    }

    public String getClassThree() {
        return classThree;
    }

    @Override
    public String toString() {
        String classification = "[" + this.classOne + " " + this.classOnePercentage + "%";
        if(!this.classTwoPercentage.isBlank()) {
            classification = classification + ", " +this.classTwo + " " + this.classTwoPercentage + "%";
        }
        if(!this.classThreePercentage.isBlank()) {
            classification = classification + ", " +this.classThree + " " + this.classThreePercentage + "%";
        }

        classification = classification + "]";
        return classification;
    }
}
