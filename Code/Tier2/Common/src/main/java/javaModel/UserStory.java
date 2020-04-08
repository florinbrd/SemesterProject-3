package javaModel;

import java.io.Serializable;
import java.util.ArrayList;

public class UserStory implements Serializable {

    private int storyPoint;
    private String userStory;
    private boolean currentlyActive;
    private int completed;
    private int groupId;
    private int sprintId;
    private int storyId;

    public UserStory() {
    }

    public int getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(int storyPoint) {
        this.storyPoint = storyPoint;
    }

    public String getUserStory() {
        return userStory;
    }

    public void setUserStory(String userStory) {
        this.userStory = userStory;
    }

    public boolean isCurrentlyActive() {
        return currentlyActive;
    }

    public void setCurrentlyActive(boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    private boolean validated;
    public ArrayList<Task> taskList;



    public int getStoryId() {
        return storyId;
    }



    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public UserStory(String userStory, int storyPoint, int completed) {
        this.userStory = userStory;
        this.storyPoint = storyPoint;
        this.completed = completed;
        currentlyActive = false;
        taskList = new ArrayList<Task>(10);
        validated = false;

    }




}
