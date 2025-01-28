public class PropertyAssessment {
    private String accountNo;
    private PropertyAddress propertyAddress;
    private String garage;
    private Neighbourhood neighbourhood;
    private String assessmentValue;
    private PointLocation pointLocation;
    private String assessmentClassification;

    public PropertyAssessment(String[] propertyData) {
        this.accountNo = propertyData[0];
        this.propertyAddress = new PropertyAddress(propertyData[1], propertyData[2], propertyData[3]);
        this.garage = propertyData[4];
        this.neighbourhood = new Neighbourhood(propertyData[5],propertyData[6],propertyData[7]);
        this.assessmentValue = propertyData[8];
        this.pointLocation = new PointLocation(propertyData[9],propertyData[10]);
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getGarage() {
        return garage;
    }
}
