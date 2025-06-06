package at.fhtechnikum.producer;

public class WeatherService {

    private static final String API_KEY = "IhrApiKey";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=Vienna&appid=";

    public boolean isSunny() {
        try {
            // Beispiel mit simplem Http-Aufruf (ohne Bibliotheken)
            java.net.URL url = new java.net.URL(BASE_URL + API_KEY);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == 200) {
                // Sehr vereinfachte Auswertung
                try (java.util.Scanner sc = new java.util.Scanner(conn.getInputStream())) {
                    String response = sc.useDelimiter("\\A").next();
                    return !response.contains("rain");
                }
            }
        } catch (Exception ignored) { }
        return false;
    }
}
