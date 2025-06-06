package at.fhtechnikum.echo2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.time.LocalDateTime;

@Repository
public interface EnergyUsageRepository extends JpaRepository<EnergyUsage, LocalDateTime> {
    Optional<EnergyUsage> findTopByOrderByHourDesc();
}