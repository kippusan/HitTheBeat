import org.omg.CORBA.SystemException;

import java.util.Map;

public class RuleManager {

    private String newStage;
    private boolean mustChangeStage = false;
    private Map<String, Integer> TargetHeartRates;
    private int CurrentHeartRate;
    private String CurrentStage; // maybe not needed
    private Integer CurrentStageCount;
    private Integer MaxStageCount;

    public void CheckRules(Map<String, Integer> targetHeartRates, int currentHeartRate, String currentStage, Integer currentStageCount, Integer maxStageCount) {
        TargetHeartRates = targetHeartRates;
        CurrentHeartRate = currentHeartRate;
        CurrentStage = currentStage;
        CurrentStageCount = currentStageCount;
        MaxStageCount = maxStageCount;


        switch (currentStage) {
            case "warmup":
                warmupRules();
                break;
            case "lowIntensity":
                lowIntensityRules();
                break;
            case "highIntensity":
                highIntensityRules();
                break;
            case "cooldown":
                cooldownRules();
                break;
            case "exit":
                newStage = CurrentStage;
                mustChangeStage = true;
                break;
        }
    }

    //have logic to say that if in x stage access heartrate and if higher than max and lower than low
    private void warmupRules() {
        if (CurrentHeartRate > TargetHeartRates.get("min-lowintensity")) {
            newStage = "lowIntensity";
            mustChangeStage = true;
        }
    }

    private void highIntensityRules() {

        if (CurrentHeartRate < TargetHeartRates.get ("min-highintensity")){
            newStage = "lowIntensity";
            System.out.println("Changed amount of stages from " + MaxStageCount + " to " + (MaxStageCount-1));
            MaxStageCount--;
            mustChangeStage = true;
        }

        if ((CurrentHeartRate >= TargetHeartRates.get("min-highintensity")) && (CurrentHeartRate < TargetHeartRates.get("max-highintensity"))) {
            newStage = CurrentStage;
            mustChangeStage = false;
        }

        if (CurrentHeartRate > TargetHeartRates.get("max-highintensity") + 20) {
            newStage = "exit";
            mustChangeStage = true;
        } else if (CurrentHeartRate >= TargetHeartRates.get("max-highintensity")) {
            newStage = "lowIntensity";
            mustChangeStage = true;
        }
    }

    private void lowIntensityRules() {

        if(CurrentHeartRate < TargetHeartRates.get("min-lowintensity")) {
            newStage = CurrentStage;
            mustChangeStage = false;
        }

        if ((CurrentHeartRate >= TargetHeartRates.get("min-lowintensity")) && (CurrentHeartRate < TargetHeartRates.get("max-lowintensity"))) {
            newStage = CurrentStage;
            mustChangeStage = false;
        }

        if (CurrentHeartRate >= TargetHeartRates.get("max-lowintensity")){
            newStage = "highIntensity";
            mustChangeStage = true;
        }

    }

    private void cooldownRules() {
    }

    public boolean getMustChangeStage() {
        return mustChangeStage;
    }

    public String getNewStage() {
        return newStage;
    }
}
