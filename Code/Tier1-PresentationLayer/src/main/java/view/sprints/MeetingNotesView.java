package view.sprints;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewmodel.sprint.MeetingNotesVM;

import java.rmi.RemoteException;

public class MeetingNotesView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text usernameLabel;

    @FXML
    private Text groupIDLabel;

    @FXML
    private Text groupRoleLabel;

    @FXML
    private ChoiceBox<String> selectTypeOfMeeting;

    @FXML
    private TextArea meetingNotesArea;

    private MeetingNotesVM meetingNotesVM;

    public void init(MeetingNotesVM meetingNotesVM){
        this.meetingNotesVM = meetingNotesVM;
        selectTypeOfMeeting.getItems().add("Daily meeting");
        selectTypeOfMeeting.getItems().add("Review meeting");
        selectTypeOfMeeting.getItems().add("Retrospective meeting");
        selectTypeOfMeeting.getItems().add("Planning meeting");
        usernameLabel.textProperty().bindBidirectional(meetingNotesVM.usernameProperty());
        groupIDLabel.textProperty().bindBidirectional(meetingNotesVM.groupIDProperty());
        groupRoleLabel.textProperty().bindBidirectional(meetingNotesVM.groupRoleProperty());
        selectTypeOfMeeting.valueProperty().bindBidirectional(meetingNotesVM.typeOfMeetingProperty());
        meetingNotesArea.textProperty().bindBidirectional(meetingNotesVM.textOfMeetingProperty());
        meetingNotesVM.refreshView();
    }

    @FXML
    void onSaveMeetingNote(ActionEvent event) throws RemoteException {
        meetingNotesVM.saveNotes();
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onBackToSprintBacklog(ActionEvent event){
        meetingNotesVM.openSprintBacklogView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        meetingNotesVM.openDashBoardView();
    }

    @FXML
    void onGroupManagementClicked(ActionEvent event) {
        meetingNotesVM.openGroupManagementView();
    }

    @FXML
    void onProductBacklogClicked(ActionEvent event) throws RemoteException {
        meetingNotesVM.openProductBacklogView();
    }

    @FXML
    void onSprintManagementClicked(ActionEvent event) {
        meetingNotesVM.openSprintManagementView();
    }

    public void onLoadNotesClicked(ActionEvent actionEvent) throws RemoteException {
        meetingNotesVM.loadNotes();

    }
}
