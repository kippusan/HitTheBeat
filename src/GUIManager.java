import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GUIManager {

    private static int SelectedProgram;
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Double> UserDataMap = new HashMap<>();
    private static int heartrate;

    public static void PromptForProgram() {

        System.out.println("Please select a training program: \n");
        System.out.println("1. Gibala intervals");
        System.out.println();
        System.out.println("Please enter selection:");

        SelectedProgram = scanner.nextInt();
    }
    public static int getSelectedProgram() {
        return SelectedProgram;
    }

    public static void PromptForUserData() {

        System.out.println ("Enter your height: ");
        double height = scanner.nextDouble();
        UserDataMap.put("height", height);

        System.out.println("Enter your age: ");
        double age = scanner.nextDouble();
        UserDataMap.put("age", age);

        System.out.println ("Enter your weight: ");
        double weight = scanner.nextDouble();
        UserDataMap.put("weight", weight);
    }
    public static Map getUserData() {
        return UserDataMap;
    }

    public static int getHeartRate() {
        return heartrate;
    }

    public static void PromptForHeartRate() {
        System.out.println("Enter heart rate: "); //TODO: remove scanner!!!!
        heartrate = scanner.nextInt();
    }
}
