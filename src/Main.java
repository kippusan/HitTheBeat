import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Date StartTime;
    private static Date EndTime;
    private static int TrainingProgram = 0; //Zero is default value, and it means that no program is selected
    private static String CurrentState = "warmup";
    private static UserCalculator userCalc;
    private static Map<String, Integer>  TargetHeartRates;
    private static Map<String, Double> UserdataStore;

    public static void main(String[] args) {
        while (true) {
            //Check for DB conn - NOT YET IMPLEMENTED
            //if (DB.isConnected()) {
            // fetch from DB
            //} else {

            //Get user data
            GUIManager.PromptForUserData();
            UserdataStore = GUIManager.getUserData();

            userCalc = new UserCalculator(); //Instantiate the user calculator instance
            int age = UserdataStore.get("age").intValue();
            TargetHeartRates = userCalc.calculateTargetHeartRate(age);
            //}

            //Let the user select a program
            GUIManager.PromptForProgram();
            TrainingProgram = GUIManager.getSelectedProgram();

            //Now we check to make sure that a program is selected.
            boolean validInput = false; //validation variable
            if (TrainingProgram > 0) { //check for real value like 1, 2 or 3
                validInput = true;
            }

            //Training loop
            if (validInput == true) {
                //Store training session start-time
                StartTime = new Date();

                while (finished == false) {

                    //if time (10 seconds) has passed

                    //get heartrate
                    System.out.println("Enter heart rate: "); //TODO: remove scanner!!!!
                    int heartrate = scanner.nextInt(); // get heartrate from console

                    ruleManager.CheckRules(StartTime, TargetHeartRates, CurrentHeartRate, CurrentState);
                    if (ruleManager.getMustChangeState() == true) { //value MustChangeState
                        //change the state
                        CurrentState = ruleManager.getNewState();

                        //Log the changed state with time and heartrate in DB
                        // DBConn.query("INSERT INTO TABLE_NAME (x, y, z) VALUES (CurrentTime, NewStage, HeartRate)")

                        //Play new music
                        musicManager.ChangeState(CurrentState);


                        //íf rulemanager decides that the training has to end: finished = true
                    }
                    //if no change, re-run loop.
                }
                //End of training
                //Save the end time
                EndTime = new Date();
                // Save relevant data to DB - WARNING: Pseudocode!
                // DBConn.query("INSERT INTO TABLE_NAME (x, y, z) VALUES (StartTime, EndTime, ...)")

            } // Else is implicit: retry asking for a input

        }
    }
}
