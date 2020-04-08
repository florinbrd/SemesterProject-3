package javaModel;

import java.io.Serializable;

public class Task implements Serializable {

    public String taskMessage;
    public int storyPoints;
    public boolean completed;
    private boolean validated;
    private int taskId;
    private int storyId;

    public Task(String taskMessage, int storyPoints, int storyId) {
        this.taskMessage = taskMessage;
        this.storyPoints = storyPoints;
        this.storyId = storyId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public boolean isValidated() {
        return validated;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Task() {
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Task(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Task(String taskMessage, int storyPoints){
        this.taskMessage = taskMessage;
        this.storyPoints = storyPoints;
        completed = false;
    }

    public void setTaskMessage(String taskMessage){
        this.taskMessage = taskMessage;
    }

    public String getTaskMessage(){
        return taskMessage;
    }

    public void setStoryPoints(int storyPoints){
        this.storyPoints = storyPoints;
    }

    public int getStoryPoints(){
        return storyPoints;
    }

    public void isCompletedByAction(){
        completed = true;
    }

    public void isCompleteByStoryPoints(){
        if(storyPoints == 0){
            completed = true;
        } else {
            completed = false;
        }
    }

    public String toString(){
        return taskMessage + " " + storyPoints + " " + completed;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Task)){
            return false;
        }

        Task other = (Task) obj;

        return taskMessage.equals(other.taskMessage) && storyPoints == other.storyPoints && completed == other.completed;
    }
}
