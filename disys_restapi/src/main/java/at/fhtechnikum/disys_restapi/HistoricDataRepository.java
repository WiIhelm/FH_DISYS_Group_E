package at.fhtechnikum.disys_restapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricDataRepository extends JpaRepository<HistoricData, java.time.LocalDateTime> {
}