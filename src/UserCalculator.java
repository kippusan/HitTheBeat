import java.util.HashMap;
import java.util.Map;

public class UserCalculator {
    public Map calculateTargetHeartRate (Integer age){  //function for calculating the THR
        Map <String, Integer> TargetHeartRates = new HashMap<>();  //create variable map that is able to store the new data with HR

        Integer maxHeartRate = 220 - age; //dynamic for the user
        TargetHeartRates.put("min-lowintensity", (int)(maxHeartRate*0.50)); //int - forced conversion into that for the THR in the hashmap
        TargetHeartRates.put("max-lowintensity", (int)(maxHeartRate*0.70));
        TargetHeartRates.put("min-highintensity", (int)(maxHeartRate*0.70));
        TargetHeartRates.put("max-highintensity", (int)(maxHeartRate*0.85));

        System.out.println("Target heart rates:");
        System.out.println("Min low-intensity: " + TargetHeartRates.get("min-lowintensity"));
        System.out.println("Max low-intensity: " + TargetHeartRates.get("max-lowintensity"));
        System.out.println("Min high-intensity: " + TargetHeartRates.get("min-highintensity"));
        System.out.println("Max high-intensity: " + TargetHeartRates.get("max-highintensity"));
        System.out.println();

        return TargetHeartRates;
    }
}
