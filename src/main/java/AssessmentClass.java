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
    }

    public AssessmentClass(String onePercentData, String twoPercentData,
                String threePercentData, String classOneData,
                String classTwoData) {
            this.classOnePercentage = onePercentData;
            this.classTwoPercentage = twoPercentData;
            this.classThreePercentage = threePercentData;
            this.classOne = classOneData;
            this.classTwo = classTwoData;
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
}
