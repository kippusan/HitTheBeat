import java.util.Date;
import java.util.Objects;

public class TimeManager {

    private String newStage;

    public boolean doneWithStage(String currentStage, Date StageStartTime) {

        boolean doneWithStage = false;
        long currentTime = new Date().getTime();

        if ((currentTime >= (StageStartTime.getTime() + (long)30000)) && (currentStage == "warmup")) {
            doneWithStage = true;
            newStage = "lowIntensity";

        } else if ((currentTime >= (StageStartTime.getTime() + (long)240000)) && (currentStage == "lowIntensity")) {
            doneWithStage = true;
            newStage = "highIntensity";

        } else if ((currentTime >= (StageStartTime.getTime() + (long)30000)) && (currentStage == "highIntensity")) {
            doneWithStage = true;
            newStage = "lowIntensity";

        }

        return doneWithStage;
    }

    public String getNewStage() {
        return newStage;
    }
}
