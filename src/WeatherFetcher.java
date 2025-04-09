import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WeatherFetcher is a simple command-line program that retrieves the daily maximum temperatures
 * for the past 30 days from the Open-Meteo API based on a given latitude and longitude.
 * 
 * It uses Java's built-in HTTP client and regular expressions to parse the JSON response.
 * The output is printed as Fahrenheit temperatures, one per line.
 */
public class WeatherFetcher {

    private final double latitude;
    private final double longitude;

    /**
     * Constructs a WeatherFetcher with the provided latitude and longitude.
     *
     * @param latitude  the geographic latitude of the location
     * @param longitude the geographic longitude of the location
     */
    public WeatherFetcher(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Builds and sends an HTTP GET request to the Open-Meteo API to retrieve
     * the past 30 days of daily maximum temperatures for the given coordinates.
     *
     * @return the JSON response body as a String, or null if the request fails
     */
    public String fetchWeatherData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(29);

        // Build the Open-Meteo API URL with query parameters
        String apiUrl = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=temperature_2m_max&start_date=%s&end_date=%s&timezone=America/Los_Angeles",
                latitude, longitude, startDate, endDate
        );

        try {
            // URI is a safe, structured way to represent the URL (preferred over using new URL(String))
            URI uri = new URI(apiUrl);

            // HttpClient is a modern Java API for making HTTP requests (Java 11+)
            HttpClient client = HttpClient.newHttpClient();

            // Create an HTTP GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            // Send the request and receive the response body as a String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body(); // Return the JSON string if successful
            }
        } catch (Exception e) {
            // Print error message if request fails
            System.out.println("Error during fetch: " + e.getMessage());
        }

        return null;
    }

    /**
     * Extracts the daily maximum temperatures from the JSON response,
     * converts them from Celsius to Fahrenheit, and prints them one per line.
     * 
     * Regular expressions are used to find and parse the temperature array.
     *
     * @param json the JSON response string from the API
     */
    private static void printMaxTemperatures(String json) {
        // Use a regular expression to extract the array of max temperatures
        Pattern pattern = Pattern.compile("\"temperature_2m_max\":\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            // Split the comma-separated string of values into individual strings
            String[] temps = matcher.group(1).split(",");

            for (String temp : temps) {
                // Parse each temperature string to a double and convert to Fahrenheit
                double celsius = Double.parseDouble(temp.trim());
                double fahrenheit = celsius * 9 / 5 + 32;
                System.out.println(fahrenheit); // Print the result with no extra formatting
            }
        }
    }

    /**
     * Entry point of the program. Expects latitude and longitude as command-line arguments.
     * Example usage: java WeatherFetcher 47.3073 -122.2285
     *
     * @param args command-line arguments: latitude and longitude
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java WeatherFetcher <latitude> <longitude>");
            return;
        }

        // Convert the input arguments to double values for lat/lon
        double lat = Double.parseDouble(args[0]);
        double lon = Double.parseDouble(args[1]);

        // Create the WeatherFetcher instance and retrieve the weather data
        WeatherFetcher fetcher = new WeatherFetcher(lat, lon);
        String json = fetcher.fetchWeatherData();

        // If data was successfully fetched, print the max temperatures
        if (json != null) {
            printMaxTemperatures(json);
        }
    }
}
