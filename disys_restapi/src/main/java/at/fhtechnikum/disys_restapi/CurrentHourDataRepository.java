package at.fhtechnikum.disys_restapi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentHourDataRepository extends JpaRepository<CurrentHourData, java.time.LocalDateTime> {
}