package at.fhtechnikum.disys_restapi;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataService {

    private final CurrentHourDataRepository currentHourDataRepository;
    private final HistoricDataRepository historicDataRepository;

    public DataService(CurrentHourDataRepository currentHourDataRepository, HistoricDataRepository historicDataRepository) {
        this.currentHourDataRepository = currentHourDataRepository;
        this.historicDataRepository = historicDataRepository;
    }

    public CurrentHourData getCurrentHourData() {
        return currentHourDataRepository.findAll().stream().findFirst()
                .orElse(null); // Assuming you want the most recent entry
    }

    public List<HistoricData> getHistoricData(LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null) {
            return historicDataRepository.findAll().stream()
                    .filter(data -> !data.getHour().isBefore(start) && !data.getHour().isAfter(end))
                    .toList();
        }
        return historicDataRepository.findAll();
    }
}