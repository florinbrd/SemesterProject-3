package javaModel;

import java.io.Serializable;

public class MeetingNotes implements Serializable {

    private String planningMeeting;
    private String retrospectiveMeeting;
    private String reviewMeeting;
    private String dailyMeeting;
    private int sprintId;
    private boolean validated;
    private int groupId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public MeetingNotes() {

    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public MeetingNotes(String planningMeeting, String retrospectiveMeeting, String reviewMeeting, String dailyMeeting) {
        this.planningMeeting = planningMeeting;
        this.retrospectiveMeeting = retrospectiveMeeting;
        this.reviewMeeting = reviewMeeting;
        this.dailyMeeting = dailyMeeting;

    }

    public String getPlanningMeeting() {
        return planningMeeting;
    }

    public void setPlanningMeeting(String planningMeeting) {


        this.planningMeeting = planningMeeting;

    }

    public String getRetrospectiveMeeting() {
        return retrospectiveMeeting;
    }

    public void setRetrospectiveMeeting(String retrospectiveMeeting) {



        this.retrospectiveMeeting = retrospectiveMeeting;

    }

    public String getReviewMeeting() {
        return reviewMeeting;
    }


    public void setReviewMeeting(String reviewMeeting) {



        this.reviewMeeting = reviewMeeting;

    }

    public String getDailyMeeting() {
        return dailyMeeting;
    }

    public void setDailyMeeting(String dailyMeeting) {
       this.dailyMeeting = dailyMeeting;
    }

}
