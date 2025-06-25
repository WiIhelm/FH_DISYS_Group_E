package at.fhtechnikum.disys_restapi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

/**
 * Repository für den Zugriff auf aktuelle Verbrauchsdaten (CurrentHourData).
 * Verwaltet Entities aus der Tabelle percentage_service.
 *
 * Primärschlüssel: LocalDateTime (hour)
 */
public interface CurrentHourDataRepository extends JpaRepository<CurrentHourData, LocalDateTime> {
    // Eigene Query-Methoden können hier ergänzt werden, z.B.:
    // Optional<CurrentHourData> findTopByOrderByHourDesc();
}