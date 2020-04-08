package view.sprints;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewmodel.sprint.SprintManagementVM;
import java.rmi.RemoteException;

public class SprintManagementView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text usernameLabel;

    @FXML
    private Text groupIDLabel;

    @FXML
    private Text groupRoleLabel;

    @FXML
    private ChoiceBox<String> selectNoSprints;

    @FXML
    private ChoiceBox<String> selectDurationSprints;

    @FXML
    private ChoiceBox<String> activeSprintChoiceBox;

    private SprintManagementVM sprintManagementVM;

    public void init(SprintManagementVM sprintManagementVM){
        this.sprintManagementVM = sprintManagementVM;
        selectNoSprints.getItems().add("3");
        selectNoSprints.getItems().add("4");
        selectNoSprints.getItems().add("5");
        selectNoSprints.getItems().add("6");
        selectNoSprints.getItems().add("7");
        selectNoSprints.getItems().add("8");
        selectDurationSprints.getItems().add("3");
        selectDurationSprints.getItems().add("4");
        selectDurationSprints.getItems().add("5");
        usernameLabel.textProperty().bindBidirectional(sprintManagementVM.usernameProperty());
        groupIDLabel.textProperty().bindBidirectional(sprintManagementVM.groupIDProperty());
        groupRoleLabel.textProperty().bindBidirectional(sprintManagementVM.groupRoleProperty());
        selectNoSprints.valueProperty().bindBidirectional(sprintManagementVM.numberOfSprintsProperty());
        selectDurationSprints.valueProperty().bindBidirectional(sprintManagementVM.durationOfSprintsProperty());
        activeSprintChoiceBox.valueProperty().bindBidirectional(sprintManagementVM.activeSprintProperty());
        sprintManagementVM.refreshView();
    }

    @FXML
    void onSetNumberClicked(ActionEvent event) throws RemoteException {
        sprintManagementVM.setNumberOfSprints();
    }

    @FXML
    void onSetDurationClicked(ActionEvent event) throws RemoteException {
        sprintManagementVM.setDurationOfSprints();
    }

    @FXML
    void onSetActiveSprint(ActionEvent event) throws RemoteException {
        sprintManagementVM.setActiveSprint();
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        sprintManagementVM.openDashBoardView();
    }

    @FXML
    void onGroupManagementClicked(ActionEvent event) throws RemoteException {
        sprintManagementVM.openGroupManagementView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onProductBacklogClicked(ActionEvent event) throws RemoteException {
        sprintManagementVM.openProductBacklogView();
    }

    @FXML
    void onSprintManagementClicked(ActionEvent event)
    {
        sprintManagementVM.openSprintManagementView();
    }

    @FXML
    void onSprintBacklogClicked(ActionEvent event) throws RemoteException {
        sprintManagementVM.openSprintBacklogView();
    }

}
