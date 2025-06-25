package at.fhtechnikum.disys_restapi;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * REST-Controller für Energie-Daten.
 * Stellt aktuelle und historische Verbrauchsdaten bereit.
 */
@RestController
@RequestMapping(DataController.API_BASE)
public class DataController {

    // Zentrale Konfiguration der API-Routen
    public static final String API_BASE = "/api";
    public static final String ENDPOINT_HOUR = "/hour";
    public static final String ENDPOINT_HISTORIC = "/historic";

    private final DataService dataService;

    /**
     * Konstruktor für Dependency Injection.
     * @param dataService Service für Datenzugriffe
     */
    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * Liefert die aktuellen Verbrauchsdaten der letzten Stunde.
     * @return Aktuelle Verbrauchsdaten
     */
    @GetMapping(ENDPOINT_HOUR)
    public CurrentHourData getCurrentHourData() {
        return dataService.getCurrentHourData();
    }

    /**
     * Liefert historische Verbrauchsdaten für einen Zeitraum.
     * Start und Ende sind optionale ISO-8601 Strings (z.B. 2024-06-01T12:00).
     * @param start Startzeitpunkt (optional)
     * @param end Endzeitpunkt (optional)
     * @return Liste der historischen Verbrauchsdaten
     */
    @GetMapping(ENDPOINT_HISTORIC)
    public List<HistoricData> getHistoricData(
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end) {
        LocalDateTime startTime = parseDateTimeOrNull(start);
        LocalDateTime endTime = parseDateTimeOrNull(end);
        return dataService.getHistoricData(startTime, endTime);
    }

    /**
     * Hilfsmethode: Wandelt einen String in LocalDateTime um, gibt bei Fehler null zurück.
     * @param value ISO-8601 Zeit-String
     * @return LocalDateTime oder null
     */
    private LocalDateTime parseDateTimeOrNull(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return LocalDateTime.parse(value);
        } catch (DateTimeParseException ex) {
            // Optional: Logging oder Fehlerbehandlung
            return null;
        }
    }
}