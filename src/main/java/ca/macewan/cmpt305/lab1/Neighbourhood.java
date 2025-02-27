package ca.macewan.cmpt305.lab1;

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

    @Override
    public String toString() {
        return this.neighbourhoodName + " (" + this.ward + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbourhood n = (Neighbourhood) o;
        return this.neighbourhoodID.equals(n.neighbourhoodID);
    }

    @Override
    public int hashCode() {
        return this.neighbourhoodID.hashCode();
    }

}
