package viewmodel.sprint;

import javaModel.IDataModel;
import javaModel.MeetingNotes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import view.ViewHandler;

import java.rmi.RemoteException;

public class MeetingNotesVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private StringProperty username;
    private StringProperty groupID;
    private StringProperty groupRole;
    private StringProperty typeOfMeeting;
    private StringProperty textOfMeeting;


    public MeetingNotesVM(IDataModel dataModel, ViewHandler viewHandler){
        this.viewHandler = viewHandler;
        this.dataModel = dataModel;
        username = new SimpleStringProperty(dataModel.getSession().getUsername());
        groupID = new SimpleStringProperty(String.valueOf(dataModel.getSession().getGroupId()));
        groupRole = new SimpleStringProperty(dataModel.getSession().getGroupRole());
        typeOfMeeting = new SimpleStringProperty();
        textOfMeeting = new SimpleStringProperty();
    }

    public void saveNotes() throws RemoteException {
        String typeOfMeetingString = typeOfMeetingProperty().getValue();
        String textOfMeetingString = textOfMeeting.getValue();
        MeetingNotes meetingNotes = new MeetingNotes();
        switch (typeOfMeetingString)
        {
            case ("Daily meeting"):
                meetingNotes.setDailyMeeting(textOfMeetingString);
                System.out.println("--------------,"+textOfMeetingString);
                break;
            case ("Review meeting"):
                meetingNotes.setReviewMeeting(textOfMeetingString);
                break;
            case ("Retrospective meeting"):
                meetingNotes.setRetrospectiveMeeting(textOfMeetingString);
                break;
            case ("Planning meeting"):
                meetingNotes.setPlanningMeeting(textOfMeetingString);
                break;
        }
        dataModel.createMinutesofTheMeeting(meetingNotes);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty groupRoleProperty() {
        return groupRole;
    }

    public StringProperty groupIDProperty() {
        return groupID;
    }

    public StringProperty typeOfMeetingProperty() {
        return typeOfMeeting;
    }

    public StringProperty textOfMeetingProperty() {
        return textOfMeeting;
    }

    public void openDashBoardView() { viewHandler.openHomepageView();}
    public void openGroupManagementView() { viewHandler.openHomepageView();}
    public void openProductBacklogView() throws RemoteException { viewHandler.openProductBacklogView();}
    public void openSprintBacklogView() { viewHandler.openSprintManagementView();}

    public void openSprintManagementView() {
        viewHandler.openSprintManagementView();
    }

    public void refreshView() {
        groupID.setValue(""+dataModel.getSession().getGroupId());
    }

    public void loadNotes() throws RemoteException {
        MeetingNotes notes = dataModel.loadMeetingNotes();
        String typeOfMeetingString = typeOfMeetingProperty().getValue();
        switch (typeOfMeetingString)
        {
            case ("Daily meeting"):
                textOfMeeting.setValue(notes.getDailyMeeting());
                break;
            case ("Review meeting"):
                textOfMeeting.setValue(notes.getReviewMeeting());
                break;
            case ("Retrospective meeting"):
                textOfMeeting.setValue(notes.getRetrospectiveMeeting());
                break;
            case ("Planning meeting"):
                textOfMeeting.setValue(notes.getPlanningMeeting());
                break;
        }
    }
}
