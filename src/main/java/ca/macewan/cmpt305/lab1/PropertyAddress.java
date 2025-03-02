package ca.macewan.cmpt305.lab1;

/**
 * Store all relevant address information for PropertyAssessment class
 */
public class PropertyAddress {
    private String suite;
    private String houseNo;
    private String streetName;

    /**
     * Constructor requires all address information for a property.
     * @param suiteData String representing suite number of address
     * @param houseData String representing house number of address
     * @param streetData String representing street name of address
     */
    public PropertyAddress(String suiteData, String houseData, String streetData) {
        this.suite = suiteData;
        this.houseNo = houseData;
        this.streetName = streetData;
    }

    /**
     * Getter method for suite number.
     * @return String representing suite number of property address
     */
    public String getSuite() {
        return suite;
    }

    /**
     * Getter method for house number.
     * @return String representing house number of property at address
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * Getter method for street name
     * @return String representing street name of property address
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     *
     * @return String representing full property address
     */
    @Override
    public String toString() {
        if(suite.isBlank()) {
            return (houseNo + " " + streetName);
        } else {
            return (suite + " " + houseNo + " " + streetName);
        }
    }

    /**
     *
     * @param o Object to compare to PropertyAddress object
     * @return True if o is a PropertyAddress object and has the same hashcode
     * value. Otherwise, return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyAddress a = (PropertyAddress) o;
        return this.hashCode() == a.hashCode();
    }

    /**
     *
     * @return Hashcode value of toString output
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
