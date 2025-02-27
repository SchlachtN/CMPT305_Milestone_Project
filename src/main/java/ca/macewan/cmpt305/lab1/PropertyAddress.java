package ca.macewan.cmpt305.lab1;

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

    @Override
    public String toString() {
        if(suite.isBlank()) {
            return (houseNo + " " + streetName);
        } else {
            return (suite + " " + houseNo + " " + streetName);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAddress a = (PropertyAddress) o;
        return this.hashCode() == a.hashCode();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
