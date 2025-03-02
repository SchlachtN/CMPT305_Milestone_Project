package ca.macewan.cmpt305.lab1;

/**
 * Stores and provides all neighbourhood information in a PropertyAssessment class.
 */
public class Neighbourhood {
    private final String neighbourhoodID;
    private String neighbourhoodName;
    private String ward;

    /**
     * Constructor requiring all important neighbourhood information as parameters.
     * @param neighbourhoodIDData String representing neighbourhood ID number
     * @param neighbourhoodData String representing neighbourhood name
     * @param wardData String representing ward name
     */
    public Neighbourhood(String neighbourhoodIDData, String neighbourhoodData, String wardData) {
        this.neighbourhoodID = neighbourhoodIDData;
        this.neighbourhoodName = neighbourhoodData;
        this.ward = wardData;
    }

    /**
     * Getter method for retrieving neighbourhood ID number.
     * @return String representing neighbourhood ID number
     */
    public String getNeighbourhoodID() {
        return neighbourhoodID;
    }

    /**
     * Getter method for retrieving neighbourhood name.
     * @return String representing neighbourhood name
     */
    public String getNeighbourhoodName() {
        return neighbourhoodName;
    }

    /**
     * Getter method for retrieving neighbourhood ward.
     * @return String representing neighbourhood ward
     */
    public String getWard() {
        return ward;
    }

    /**
     *
     * @return String containing neighbourhood name and ward
     */
    @Override
    public String toString() {
        return this.neighbourhoodName + " (" + this.ward + ")";
    }

    /**
     *
     * @param o Object to compare to Neighbourhood object
     * @return True if o is a Neighbourhood object and has the same
     * neighbourhood ID number. Otherwise, return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbourhood n = (Neighbourhood) o;
        return this.neighbourhoodID.equals(n.neighbourhoodID);
    }

    /**
     *
     * @return Hashcode value of neighbourhood ID
     */
    @Override
    public int hashCode() {
        return this.neighbourhoodID.hashCode();
    }

}
