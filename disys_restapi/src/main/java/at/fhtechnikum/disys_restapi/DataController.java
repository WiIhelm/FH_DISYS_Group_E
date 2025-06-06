package at.fhtechnikum.disys_restapi;

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
    public List<HistoricData> getHistoricData(@RequestParam(required = false) String start,
                                              @RequestParam(required = false) String end) {
        return dataService.getHistoricData(start != null ? LocalDateTime.parse(start) : null,
                end != null ? LocalDateTime.parse(end) : null);
    }
}