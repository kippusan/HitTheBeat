import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This is the main program class (test)
 * @author Hristina
 *
 */
public class HeartRateDecipherer {

	private static Scanner scanner = new Scanner(System.in);
	private static MusicManager musicManager = new MusicManager();
	private static UserDataCollector dataCollector = new UserDataCollector();
	private static UserCalculator userCalculator = new UserCalculator();
	private static Map<String, Double> DataStore = new HashMap<>();
	private static Map<String, Integer> TargetHeartRates = new HashMap<>();

	public static void main(String[] args) {

		DataStore = dataCollector.getData(); //store the data fom the collector in the hashmap
		TargetHeartRates = userCalculator.calculateTargetHeartRate(DataStore.get("age").intValue()); //convert into int from double
		System.out.println("min-lowintensity: " + TargetHeartRates.get("min-lowintensity"));
		System.out.println("max-lowintensity: " + TargetHeartRates.get("max-lowintensity"));
		System.out.println("min-highintensity: " + TargetHeartRates.get("min-highintensity"));
		System.out.println("max-highintensity: " + TargetHeartRates.get("max-highintensity"));

		boolean exit = false;
		while (exit == false) {
			String genre = "Warm up";

			//System.out.println("Enter heart rate: ");
			//int heartrate = scanner.nextInt(); // get heartrate from console
			
			//add matrix rules
			musicManager.ChangeGenre(genre);
		}
	}
}