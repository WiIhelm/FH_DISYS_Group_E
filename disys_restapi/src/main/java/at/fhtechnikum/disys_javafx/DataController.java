package at.fhtechnikum.disys_javafx;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/hour")
    public CurrentHourData getCurrentHourData() {
        return dataService.getCurrentHourData();
    }

    @GetMapping("/historic")
    public List<HistoricData> getHistoricData(
            @RequestParam String start,
            @RequestParam String end
    ) {
        LocalDateTime startTime = LocalDateTime.parse(start);
        LocalDateTime endTime = LocalDateTime.parse(end);
        return dataService.getHistoricData(startTime, endTime);
    }
}
