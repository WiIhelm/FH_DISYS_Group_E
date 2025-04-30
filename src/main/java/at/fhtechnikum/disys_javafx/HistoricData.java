package at.fhtechnikum.disys_javafx;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricData {

    @JsonProperty
    private double produced;

    @JsonProperty
    private double used;

    @JsonProperty
    private double gridUsed;

    public HistoricData() {
    }

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
}