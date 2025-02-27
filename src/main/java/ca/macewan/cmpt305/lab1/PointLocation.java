package ca.macewan.cmpt305.lab1;

public class PointLocation {
    private String latitude;
    private String longitude;

    public PointLocation(String latitudeData, String longitudeData) {
        this.latitude = latitudeData;
        this.longitude = longitudeData;
    }

    @Override
    public String toString() {
        return "(" + this.latitude + ", " + this.longitude + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointLocation p = (PointLocation) o;
        return this.latitude.equals(p.latitude) && this.longitude.equals(p.longitude);
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
