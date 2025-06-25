package at.fhtechnikum.disys_javafx;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Datenmodell für aktuelle Energiewerte der letzten Stunde.
 * Enthält den Anteil des Community-Pools und des Netzbezugs.
 */
public class CurrentHourData {

    /** Anteil des Community-Pools, der verbraucht wurde (in Prozent) */
    @JsonProperty("communityDepleted")
    private double communityPool;

    /** Anteil des Netzbezugs (in Prozent) */
    @JsonProperty("gridPortion")
    private double gridPortion;

    /** Standard-Konstruktor für Deserialisierung */
    public CurrentHourData() {
    }

    /**
     * Konstruktor mit allen Feldern.
     * @param communityPool Anteil des Community-Pools
     * @param gridPortion Anteil des Netzbezugs
     */
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

    @Override
    public String toString() {
        return "CurrentHourData{" +
                "communityPool=" + communityPool +
                ", gridPortion=" + gridPortion +
                '}';
    }
}