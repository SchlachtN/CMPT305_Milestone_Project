public class PropertyAddress {
    private String suite;
    private String houseNo;
    private String streetName;

    public PropertyAddress(String suiteData, String houseData, String streetData) {
        this.suite = suiteData;
        this.houseNo = houseData;
        this.streetName = streetData;
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

    public String getAddress() {
        if(suite.isBlank()) {
            return (houseNo + " " + streetName);
        } else {
            return (suite + " " + houseNo + " " + streetName);
        }
    }
}
