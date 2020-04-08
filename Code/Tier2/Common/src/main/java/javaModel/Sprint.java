package javaModel;

import java.io.Serializable;
import java.util.ArrayList;

public class Sprint implements Serializable {

    private int sprintNumber;
    private ArrayList<UserStory> sprintStories;
    private boolean completed;
    private MeetingNotes meetingNotes;
    private int sprintId;
    private int groupId;

    public Sprint ()
    {

    }
    public Sprint(int sprintNumber){
        this.sprintNumber = sprintNumber;
        sprintStories = new ArrayList<UserStory>(20);
        completed = false;
        meetingNotes = null;
    }

    public void setSprintNumber(int sprintNumber){
        this.sprintNumber = sprintNumber;
    }

    public int getSprintNumber(){
        return sprintNumber;
    }

    public int getNoOfUserStories(){
        return sprintStories.size();
    }

    public void addUserStory(UserStory userStory){
        sprintStories.add(userStory);
    }

    public void setCompletedByAction(){
        completed = true;
    }

    public void setCompletedByPoints(){
        for(int i=0; i< sprintStories.size(); i++){
            if(sprintStories.get(i).getStoryPoint() == 0){
                completed = true;
            }
        }
    }

    public MeetingNotes getMeetingNotes() {
        return meetingNotes;
    }

    public void setMeetingNotes(MeetingNotes meetingNotes) {
        this.meetingNotes = meetingNotes;
    }

    public void setDailyMeeting(String dailyMeeting){
        meetingNotes.setDailyMeeting(dailyMeeting);
    }

    public void setReviewMeeting(String reviewMeeting){
        meetingNotes.setReviewMeeting(reviewMeeting);
    }

    public void setPlanningMeeting(String planningMeeting){
        meetingNotes.setPlanningMeeting(planningMeeting);
    }

    public void setRetrospectiveMeeting(String retrospectiveMeeting){
        meetingNotes.setRetrospectiveMeeting(retrospectiveMeeting);
    }

    public ArrayList<UserStory> getUserStories() {
        return sprintStories;
    }
    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public ArrayList<UserStory> getSprintStories() {
        return sprintStories;
    }

    public void setSprintStories(ArrayList<UserStory> sprintStories) {
        this.sprintStories = sprintStories;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
