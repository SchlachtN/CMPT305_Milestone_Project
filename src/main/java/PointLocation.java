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
}
