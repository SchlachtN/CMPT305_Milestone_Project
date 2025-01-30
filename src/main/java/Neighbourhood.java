public class Neighbourhood {
    private String neighbourhoodID;
    private String neighbourhoodName;
    private String ward;

    public Neighbourhood(String neighbourhoodIDData, String neighbourhoodData, String wardData) {
        this.neighbourhoodID = neighbourhoodIDData;
        this.neighbourhoodName = neighbourhoodData;
        this.ward = wardData;
    }

    public String getNeighbourhoodID() {
        return neighbourhoodID;
    }

    public String getNeighbourhoodName() {
        return neighbourhoodName;
    }

    public String getWard() {
        return ward;
    }

    public String getNeighbourhood() {
        return neighbourhoodName + " (" + ward + " Ward)";
    }


}
