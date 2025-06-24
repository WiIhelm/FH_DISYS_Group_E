package at.technikum.energyuser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnergyUserTest {
    @Test
    void testToStringFormat() {
        ConsumerMessage msg = new ConsumerMessage(42.5);
        String result = msg.toString();

        System.out.println(result);

        assertTrue(result.contains("type:USER"));
        assertTrue(result.contains("association:COMMUNITY"));
        assertTrue(result.contains("kwh:42.5"));
        assertTrue(result.contains("datetime:"));
        assertTrue(true);
    }
}