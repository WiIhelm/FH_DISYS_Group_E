import at.fhtechnikum.echo.EnergyUsageRepository;
import at.fhtechnikum.echo.UsageService;
import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.*;

public class UsageServiceTest {

    private UsageService usageService;
    private EnergyUsageRepository energyUsageRepository;
    private Channel channel;

    @BeforeEach
    public void setUp() {

        energyUsageRepository = mock(EnergyUsageRepository.class);
        channel = mock(Channel.class);

        usageService = new UsageService();

        // inject mock repo
        try {
            var repoField = UsageService.class.getDeclaredField("repo");
            repoField.setAccessible(true);
            repoField.set(usageService, energyUsageRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testProcessTextSavesEnergyUsage() throws Exception {
    }
}
