package at.fhtechnikum.echo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EnergyUsageRepository extends JpaRepository<EnergyUsage, LocalDateTime> {
    Optional<EnergyUsage> findTopByOrderByHourDesc();
}