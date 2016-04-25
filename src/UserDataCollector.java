import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserDataCollector {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Double> DataStore = new HashMap<>();

    public Map getData() {
        DataStore.put("age", getAge());
        DataStore.put("weight", getWeight());
        DataStore.put("height", getHeight());
        return DataStore;
    }

    private double getAge() {
        System.out.println("Enter your age: ");
        return scanner.nextDouble(); // get age from console
    }
    private double getWeight(){
        System.out.println ("Enter your weight: ");
        return scanner.nextDouble();
    }

    private double getHeight() {
        System.out.println ("Enter your height: ");
        return scanner.nextDouble();
    }

}
