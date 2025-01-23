public class PropertyAssessment {
    private String accountNo;
    private String propertyAddress;
    private String garage;
    private String neighbourhood;
    private String assessmentValue;
    private String pointLocation;
    private String assessmentClassification;

    public PropertyAssessment(String[] propertyData) {
        this.accountNo = getAccountNo();
        this.propertyAddress = getPropertyAddress();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }
}
