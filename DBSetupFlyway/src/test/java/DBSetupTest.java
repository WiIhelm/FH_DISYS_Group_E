import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBSetupTest {

    // checks if the tables that we need for the different modules were really created
    @Test
    public void testTablesCreated() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "disysuser";
        String password = "disyspw";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData metaData = connection.getMetaData();

            boolean foundEnergyUsage = false;
            boolean foundPercentageService = false;

            try (ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"})) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    if (tableName.equals("energy_usage")) { // TODO equalsIgnoreCase?
                        foundEnergyUsage = true;
                    }

                    if (tableName.equals("percentage_service")) {
                        foundPercentageService = true;
                    }
                }
            }

            assertTrue(foundEnergyUsage, "energy_usage table doesn't exist");
            assertTrue(foundPercentageService, "percentage_service table doesn't exist");

        }
    }

}
