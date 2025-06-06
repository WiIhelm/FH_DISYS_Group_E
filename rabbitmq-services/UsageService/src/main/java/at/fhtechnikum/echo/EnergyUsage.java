package at.fhtechnikum.echo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "energy_usage")
public class EnergyUsage {
    @Id
    @Column(name = "hour", nullable = false)
    private LocalDateTime hour;

    @Column(name = "community_produced")
    private double communityProduced;

    @Column(name = "community_used")
    private double communityUsed;

    @Column(name = "grid_used")
    private double gridUsed;

    protected EnergyUsage() {}

    public EnergyUsage(LocalDateTime hour) {
        this.hour = hour;
    }

    public LocalDateTime getHour() { return hour; }
    public double getCommunityProduced() { return communityProduced; }
    public void setCommunityProduced(double val) { this.communityProduced = val; }
    public double getCommunityUsed() { return communityUsed; }
    public void setCommunityUsed(double val) { this.communityUsed = val; }
    public double getGridUsed() { return gridUsed; }
    public void setGridUsed(double val) { this.gridUsed = val; }
}