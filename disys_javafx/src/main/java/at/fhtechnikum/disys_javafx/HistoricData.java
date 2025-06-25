package at.fhtechnikum.disys_javafx;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Datenmodell für historische Energiewerte einer Zeitspanne.
 * Enthält produzierte, verbrauchte und aus dem Netz bezogene Energie.
 */
public class HistoricData {

    /** Von der Community produzierte Energie (kWh) */
    @JsonProperty("communityProduced")
    private double produced;

    /** Von der Community verbrauchte Energie (kWh) */
    @JsonProperty("communityUsed")
    private double used;

    /** Aus dem Netz bezogene Energie (kWh) */
    @JsonProperty("gridUsed")
    private double gridUsed;

    /** Standard-Konstruktor für Deserialisierung */
    public HistoricData() {
    }

    /**
     * Konstruktor mit allen Feldern.
     * @param produced produzierte Energie
     * @param used verbrauchte Energie
     * @param gridUsed Netzbezug
     */
    public HistoricData(double produced, double used, double gridUsed) {
        this.produced = produced;
        this.used = used;
        this.gridUsed = gridUsed;
    }

    public double getProduced() {
        return produced;
    }

    public void setProduced(double produced) {
        this.produced = produced;
    }

    public double getUsed() {
        return used;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public double getGridUsed() {
        return gridUsed;
    }

    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }

    @Override
    public String toString() {
        return "HistoricData{" +
                "produced=" + produced +
                ", used=" + used +
                ", gridUsed=" + gridUsed +
                '}';
    }
}