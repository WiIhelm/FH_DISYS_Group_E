package at.fhtechnikum.disys_javafx;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    public CurrentHourData getCurrentHourData() {
        double communityPool = 75.0 + (Math.random() * 10 - 5);
        double gridPortion = 25.0 + (Math.random() * 5 - 2.5);
        return new CurrentHourData(communityPool, gridPortion);
    }

    public List<HistoricData> getHistoricData(LocalDateTime start, LocalDateTime end) {
        List<HistoricData> list = new ArrayList<>();
        for (int hour = start.getHour(); hour <= end.getHour(); hour++) {
            double produced = 150 + hour * 10 + (Math.random() * 10 - 5);
            double used = produced - (10 + hour * 2) + (Math.random() * 5 - 2.5);
            double gridUsed = (produced / 10) + (Math.random() * 5 - 2.5);
            list.add(new HistoricData(produced, used, gridUsed));
        }
        return list;
    }
}