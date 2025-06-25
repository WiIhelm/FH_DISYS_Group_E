package at.fhtechnikum.disys_restapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * Entity für die aktuellen Verbrauchsdaten einer Stunde.
 * Wird in der Tabelle PERCENTAGE_SERVICE gespeichert.
 */
@Entity
@Table(name = CurrentHourData.TABLE_NAME)
public class CurrentHourData {

    // Zentrale Konfiguration: Tabellenname
    public static final String TABLE_NAME = "percentage_service";

    /**
     * Zeitstempel der Stunde (Primärschlüssel).
     */
    @Id
    private LocalDateTime hour;

    /**
     * Prozentualer Anteil, der aus dem Community-Pool entnommen wurde.
     */
    @JsonProperty
    private double communityDepleted;

    /**
     * Prozentualer Anteil, der aus dem Netz bezogen wurde.
     */
    @JsonProperty
    private double gridPortion;

    /**
     * Standard-Konstruktor (wird von JPA benötigt).
     */
    public CurrentHourData() {
    }

    /**
     * Konstruktor für alle Felder.
     * @param hour Zeitstempel der Stunde
     * @param communityDepleted Anteil aus Community-Pool
     * @param gridPortion Anteil aus Netz
     */
    public CurrentHourData(LocalDateTime hour, double communityDepleted, double gridPortion) {
        this.hour = hour;
        this.communityDepleted = communityDepleted;
        this.gridPortion = gridPortion;
    }

    /** @return Zeitstempel der Stunde */
    public LocalDateTime getHour() {
        return hour;
    }

    /** @param hour Zeitstempel der Stunde */
    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }

    /** @return Anteil aus Community-Pool */
    public double getCommunityDepleted() {
        return communityDepleted;
    }

    /** @param communityDepleted Anteil aus Community-Pool */
    public void setCommunityDepleted(double communityDepleted) {
        this.communityDepleted = communityDepleted;
    }

    /** @return Anteil aus Netz */
    public double getGridPortion() {
        return gridPortion;
    }

    /** @param gridPortion Anteil aus Netz */
    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}