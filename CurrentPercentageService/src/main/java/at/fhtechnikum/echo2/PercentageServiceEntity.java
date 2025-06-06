package at.fhtechnikum.echo2;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "percentage_service")
public class PercentageServiceEntity {
    @Id
    @Column(name = "hour", nullable = false)
    private LocalDateTime hour;

    @Column(name = "community_depleted", nullable = false)
    private double communityDepleted;

    @Column(name = "grid_portion", nullable = false)
    private double gridPortion;

    protected PercentageServiceEntity() {}

    public LocalDateTime getHour() { return hour; }
    public void setHour(LocalDateTime hour) { this.hour = hour; }

    public double getCommunityDepleted() { return communityDepleted; }
    public void setCommunityDepleted(double val) { this.communityDepleted = val; }

    public double getGridPortion() { return gridPortion; }
    public void setGridPortion(double val) { this.gridPortion = val; }
}