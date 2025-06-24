package at.fhtechnikum.producer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProducerTest {

    @Test
    void testToStringFormat() {
        ProducerMessage msg = new ProducerMessage(42.5);
        String result = msg.toString();

        System.out.println(result);

        assertTrue(result.contains("type:PRODUCER"));
        assertTrue(result.contains("association:COMMUNITY"));
        assertTrue(result.contains("kwh:42.5"));
        assertTrue(result.contains("datetime:"));
        assertTrue(true);
    }


    // ***** WEATHER SERVICE TESTS *******
    @Test
    void testIsSunnyResponse() {
        WeatherService weatherService = new WeatherService() {
            @Override
            public boolean isSunny() {
                return true;
            }
        };
        assertTrue(weatherService.isSunny());
    }
}
