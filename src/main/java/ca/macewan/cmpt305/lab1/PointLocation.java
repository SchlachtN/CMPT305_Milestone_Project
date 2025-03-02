package ca.macewan.cmpt305.lab1;

/**
 * Simple class for representing geographic coordinate location information
 * for PropertyAssessment class.
 */
public class PointLocation {
    private String latitude;
    private String longitude;

    /**
     * Constructor requires latitude and longitude data for coordinates.
     * @param latitudeData String representing property latitude
     * @param longitudeData String representing property longitude
     */
    public PointLocation(String latitudeData, String longitudeData) {
        this.latitude = latitudeData;
        this.longitude = longitudeData;
    }

    /**
     *
     * @return String representation of geographic coordinates
     */
    @Override
    public String toString() {
        return "(" + this.latitude + ", " + this.longitude + ")";
    }

    /**
     *
     * @param o Object to compare PointLocation object with
     * @return True if o is a PointLocation object and the same latitude and
     * longitude values. Otherwise, return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointLocation p = (PointLocation) o;
        return this.latitude.equals(p.latitude) && this.longitude.equals(p.longitude);
    }

    /**
     *
     * @return Hashcode value from toString method
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
