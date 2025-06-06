package at.fhtechnikum.disys_restapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "percentage_service")
public class CurrentHourData {

    @Id
    private java.time.LocalDateTime hour;

    @JsonProperty
    private double communityDepleted;

    @JsonProperty
    private double gridPortion;

    public CurrentHourData() {
    }

    public CurrentHourData(java.time.LocalDateTime hour, double communityDepleted, double gridPortion) {
        this.hour = hour;
        this.communityDepleted = communityDepleted;
        this.gridPortion = gridPortion;
    }

    public java.time.LocalDateTime getHour() {
        return hour;
    }

    public void setHour(java.time.LocalDateTime hour) {
        this.hour = hour;
    }

    public double getCommunityDepleted() {
        return communityDepleted;
    }

    public void setCommunityDepleted(double communityDepleted) {
        this.communityDepleted = communityDepleted;
    }

    public double getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}