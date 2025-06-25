package at.fhtechnikum.disys_restapi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * Entity für historische Energiedaten einer Stunde.
 * Wird in der Tabelle energy_usage gespeichert.
 */
@Entity
@Table(name = HistoricData.TABLE_NAME)
public class HistoricData {

    /** Zentrale Konfiguration: Tabellenname */
    public static final String TABLE_NAME = "energy_usage";

    /**
     * Zeitstempel der Stunde (Primärschlüssel).
     */
    @Id
    private LocalDateTime hour;

    /**
     * Energie (kWh), die von der Community produziert wurde.
     */
    @JsonProperty
    private double communityProduced;

    /**
     * Energie (kWh), die von der Community verbraucht wurde.
     */
    @JsonProperty
    private double communityUsed;

    /**
     * Energie (kWh), die aus dem Netz bezogen wurde.
     */
    @JsonProperty
    private double gridUsed;

    /** Standard-Konstruktor (wird von JPA benötigt). */
    public HistoricData() {
    }

    /**
     * Konstruktor für alle Felder.
     * @param hour Zeitstempel der Stunde
     * @param communityProduced von der Community produziert
     * @param communityUsed von der Community verbraucht
     * @param gridUsed aus dem Netz bezogen
     */
    public HistoricData(LocalDateTime hour, double communityProduced, double communityUsed, double gridUsed) {
        this.hour = hour;
        this.communityProduced = communityProduced;
        this.communityUsed = communityUsed;
        this.gridUsed = gridUsed;
    }

    /** @return Zeitstempel der Stunde */
    public LocalDateTime getHour() {
        return hour;
    }

    /** @param hour Zeitstempel der Stunde */
    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }

    /** @return von der Community produziert */
    public double getCommunityProduced() {
        return communityProduced;
    }

    /** @param communityProduced von der Community produziert */
    public void setCommunityProduced(double communityProduced) {
        this.communityProduced = communityProduced;
    }

    /** @return von der Community verbraucht */
    public double getCommunityUsed() {
        return communityUsed;
    }

    /** @param communityUsed von der Community verbraucht */
    public void setCommunityUsed(double communityUsed) {
        this.communityUsed = communityUsed;
    }

    /** @return aus dem Netz bezogen */
    public double getGridUsed() {
        return gridUsed;
    }

    /** @param gridUsed aus dem Netz bezogen */
    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }
}