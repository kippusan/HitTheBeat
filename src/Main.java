import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static Date StartTime;
    private static Date EndTime;
    private static String CurrentStage = "warmup";
    private static UserCalculator userCalc;
    private static Map<String, Integer>  TargetHeartRates;
    private static Map<String, Double> UserdataStore;
    private static RuleManager ruleManager = new RuleManager();
    private static TimeManager timeManager = new TimeManager();
    private static MusicManager musicManager  = new MusicManager();
    private static Integer MaxStageCount = 6;
    private static Integer CurrentStageCount = 1;
    private static ExecutorService musicExec = Executors.newFixedThreadPool(1);
    static Future future = null;
    private static String OldStage;


    public static void main(String[] args) throws SQLException {

            while (true) {
            //Check for DB conn - NOT YET IMPLEMENTED
            if (Select.setSel("*", "users").getFetchSize() <= 1) {

                //fetch from DB
                ResultSet resultdata = Select.setSel('', 'userdata');
                for (Object data : resultdata) {
                    TargetHeartRates.put("min-lowintensity", (int)(maxHeartRate*0.50)); //int - forced conversion into that for the THR in the hashmap
                    TargetHeartRates.put("max-lowintensity", (int)(maxHeartRate*0.70));
                    TargetHeartRates.put("min-highintensity", (int)(maxHeartRate*0.70));
                    TargetHeartRates.put("max-highintensity", (int)(maxHeartRate*0.85));
                }
            } else {

                //Get user data
                GUIManager.PromptForUserData();
                UserdataStore = GUIManager.getUserData();



                userCalc = new UserCalculator(); //Instantiate the user calculator instance
                int age = UserdataStore.get("age").intValue();
                TargetHeartRates = userCalc.calculateTargetHeartRate(age);
            }

            //Let the user select a program
            GUIManager.PromptForProgram();
            int trainingProgram = GUIManager.getSelectedProgram();

            //Now we check to make sure that a program is selected.
            boolean validInput = false; //validation variable
            if (trainingProgram > 0) { //check for real value like 1, 2 or 3
                validInput = true;
            }

            //Training loop
            if (validInput == true) {
                //Store training session start-time
                StartTime = new Date();
                Date stageStartTime = new Date();


                runMusicThread(); // start the music player in a secondary thread

                System.out.println("Current stage is: " + CurrentStageCount);

                boolean finished = false;
                while (!finished) {

                    try {
                        //Pause the program for 10 seconds
                        //Thread is the execution of program
                        Thread.sleep(10000); //10000 milliseconds is 10 seconds.
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt(); //Interrupt the program if there is an error
                    }

                    //if time for the stage has not passed and we are still running the training
                    if (timeManager.doneWithStage(CurrentStage, stageStartTime) == false) {

                        //get heartrate
                        GUIManager.PromptForHeartRate(); // get heartrate from console
                        int CurrentHeartRate = GUIManager.getHeartRate();



                        ruleManager.CheckRules(TargetHeartRates, CurrentHeartRate, CurrentStage, CurrentStageCount, MaxStageCount);
                        if (ruleManager.getMustChangeStage()) { //value MustChangeState

                            //change the state
                            OldStage = CurrentStage;
                            CurrentStage = ruleManager.getNewStage();

                            if(CurrentStage != "exit") {
                                if (CurrentStage != OldStage) {
                                    System.out.println("Stage changed to: " + CurrentStage);
                                    stageStartTime = new Date();

                                    //Log the changed stage with time and heartrate in DB


                                    //Play new music
                                    runMusicThread(); // start the music player in a secondary thread
                                }

                            } else {
                                //íf rulemanager decides that the training has to end:

                                // End music thread if running
                                musicExec.shutdownNow();

                                finished = true;
                                System.out.println("Stage changed to: " + CurrentStage);
                            }


                            CurrentStageCount++;
                            System.out.println("Current stage is: " + CurrentStageCount);
                        }

                    } else {
                        CurrentStageCount++;
                        System.out.println("Stage time has passed.");
                        System.out.println("Current stage count is: " + CurrentStageCount);

                        CurrentStage = timeManager.getNewStage();
                        System.out.println("Changing to stage: " + CurrentStage);
                        stageStartTime = new Date();

                        // change to new playlist for new stage

                        runMusicThread();
                    }
                    //if no change, re-run loop.


                    if (CurrentStageCount >= MaxStageCount) {
                        // End music thread if running
                        musicExec.shutdownNow();


                        finished = true;
                        System.out.println("Done with training");
                    }
                }
                //End of training

                //Store the end time in a variable
                EndTime = new Date();

                // Save relevant data to DB - WARNING: Pseudocode!
                // DBConn.query("INSERT INTO TABLE_NAME (x, y, z) VALUES (StartTime, EndTime, ...)")

            } // Else is implicit: retry asking for a input

        }
    }

    private static void runMusicThread() {
        if (future != null) {
            future.cancel(true);
        }

        //The future object holds an executor for managing a secondary thread safely. It also provides access to cancel the thread
        future = musicExec.submit(new Runnable() {
            public void run() {
                MusicManager music = new MusicManager();
                music.playPlaylist(CurrentStage);
            }
        });
    }
}
