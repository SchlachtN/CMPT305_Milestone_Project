public class PropertyAddress {
    private String suite;
    private String houseNo;
    private String streetName;

    public PropertyAddress() {
        this.suite = getSuite();
        this.houseNo = getHouseNo();
        this.streetName = getStreetName();
    }

    public String getSuite() {
        return suite;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public String getStreetName() {
        return streetName;
    }
}
