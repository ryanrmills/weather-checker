import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherFetcher {

    private final double latitude;
    private final double longitude;

    public WeatherFetcher(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String fetchWeatherData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(30);

        String apiUrl = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=temperature_2m_max&start_date=%s&end_date=%s&timezone=America/Los_Angeles",
                latitude, longitude, startDate, endDate
        );

        try {
            URI uri = new URI(apiUrl);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            }
        } catch (Exception e) {
            System.out.println("Error during fetch: " + e.getMessage());
        }

        return null;
    }

    private static void printMaxTemperatures(String json) {
        Pattern pattern = Pattern.compile("\"temperature_2m_max\":\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            String[] temps = matcher.group(1).split(",");
            for (String temp : temps) {
                double celsius = Double.parseDouble(temp.trim());
                double fahrenheit = celsius * 9 / 5 + 32;
                System.out.println(fahrenheit);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java WeatherFetcher <latitude> <longitude>");
            return;
        }

        double lat = Double.parseDouble(args[0]);
        double lon = Double.parseDouble(args[1]);

        WeatherFetcher fetcher = new WeatherFetcher(lat, lon);
        String json = fetcher.fetchWeatherData();

        if (json != null) {
            printMaxTemperatures(json);
        }
    }
}
