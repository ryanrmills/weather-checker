import java.util.Scanner;

public class WeatherSummary {
    /**
     * Reads newline-delimted temperatures from System.in and prints summary
     * statistics to System.out.
     * 
     * Example input:
     * 66.4
     * 77.1
     * 72.6
     * 
     * Example output:
     * Max: 66.4
     * Min: 77.1
     * Average: 72.03333333333333
     * 
     * @param args command line arguments (ignored)
     */
    public static void main(String[] args) {
        // Implement this method!
        // Hint: use Scanner. nextDouble() and hasNextDouble() will be helpful here!

        Scanner scan = new Scanner(System.in);

        double minTemp = scan.nextDouble();
        double maxTemp = 0;
        double sum = 0;
        int count = 0;
        

        while (scan.hasNextDouble()){
            double currTemp = scan.nextDouble();
            
            sum += currTemp;
            count++;

            minTemp = Math.min(minTemp, currTemp);
            maxTemp = Math.max(maxTemp, currTemp);
        }

        System.out.println("Max: " + maxTemp);
        System.out.println("Min: " + minTemp);
        System.out.println("Average: " + (sum/count));
        
    }
}
