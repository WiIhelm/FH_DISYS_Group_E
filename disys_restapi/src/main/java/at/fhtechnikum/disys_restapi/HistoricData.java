package at.fhtechnikum.disys_restapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "energy_usage")
public class HistoricData {

    @Id
    private java.time.LocalDateTime hour;

    @JsonProperty
    private double communityProduced;

    @JsonProperty
    private double communityUsed;

    @JsonProperty
    private double gridUsed;

    public HistoricData() {
    }

    public HistoricData(java.time.LocalDateTime hour, double communityProduced, double communityUsed, double gridUsed) {
        this.hour = hour;
        this.communityProduced = communityProduced;
        this.communityUsed = communityUsed;
        this.gridUsed = gridUsed;
    }

    public java.time.LocalDateTime getHour() {
        return hour;
    }

    public void setHour(java.time.LocalDateTime hour) {
        this.hour = hour;
    }

    public double getCommunityProduced() {
        return communityProduced;
    }

    public void setCommunityProduced(double communityProduced) {
        this.communityProduced = communityProduced;
    }

    public double getCommunityUsed() {
        return communityUsed;
    }

    public void setCommunityUsed(double communityUsed) {
        this.communityUsed = communityUsed;
    }

    public double getGridUsed() {
        return gridUsed;
    }

    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }
}