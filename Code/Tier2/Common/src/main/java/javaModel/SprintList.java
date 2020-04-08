package javaModel;

import java.io.Serializable;
import java.util.ArrayList;

public class SprintList implements Serializable {

    public int duration;
    public ArrayList<Sprint> sprintsList;
    public int numberOfSprints;
    public int groupID;
    public int currentlyActiveSprint;
    private boolean validated;

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public SprintList ()
    {

    }
    public SprintList(int duration){
        this.duration = duration;
        sprintsList = new ArrayList<>();
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public int getDuration(){
        return  duration;
    }

    public ArrayList<Sprint> getSprintsList() {
        return sprintsList;
    }

    public void setSprintsList(ArrayList<Sprint> sprintsList) {
        this.sprintsList = sprintsList;
    }

    public int getNumberOfSprints() {
        return numberOfSprints;
    }

    public void setNumberOfSprints(int numberOfSprints) {
        this.numberOfSprints = numberOfSprints;
    }

    public int getCurrentlyActiveSprint() {
        return currentlyActiveSprint;
    }

    public void setCurrentlyActiveSprint(int currentlyActiveSprint) {
        this.currentlyActiveSprint = currentlyActiveSprint;
    }


}
