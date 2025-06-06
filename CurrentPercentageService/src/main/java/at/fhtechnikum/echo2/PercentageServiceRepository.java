package at.fhtechnikum.echo2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface PercentageServiceRepository extends JpaRepository<PercentageServiceEntity, LocalDateTime> {
}