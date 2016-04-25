import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for calculating the target heart rate of the user
 */
public class UserCalculator {
    public Map calculateTargetHeartRate (Integer age){  //function for calculating the THR
        Map <String, Integer> TargetHeartRates = new HashMap<>();  //create variable map that is able to store the new data with HR

        Integer maxHeartRate = 220 - age;
        TargetHeartRates.put("min-lowintensity", (int)(maxHeartRate*0.50)); //int - forced conversion into that for the THR in the hashmap
        TargetHeartRates.put("max-lowintensity", (int)(maxHeartRate*0.70));
        TargetHeartRates.put("min-highintensity", (int)(maxHeartRate*0.70));
        TargetHeartRates.put("max-highintensity", (int)(maxHeartRate*0.85));

        return TargetHeartRates;
    }
}
