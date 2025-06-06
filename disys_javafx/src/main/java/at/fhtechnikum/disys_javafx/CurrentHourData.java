package at.fhtechnikum.disys_javafx;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentHourData {

    @JsonProperty("communityDepleted")
    private double communityPool;

    @JsonProperty("gridPortion")
    private double gridPortion;

    public CurrentHourData() {
    }

    public CurrentHourData(double communityPool, double gridPortion) {
        this.communityPool = communityPool;
        this.gridPortion = gridPortion;
    }

    public double getCommunityPool() {
        return communityPool;
    }

    public void setCommunityPool(double communityPool) {
        this.communityPool = communityPool;
    }

    public double getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}