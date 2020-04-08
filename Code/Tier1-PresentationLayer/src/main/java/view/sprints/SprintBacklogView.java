package view.sprints;

import javaModel.Task;
import javaModel.UserStory;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import viewmodel.sprint.SprintBacklogVM;

import javax.swing.*;
import java.rmi.RemoteException;

public class SprintBacklogView {
    @FXML
    private Text usernameLabel;

    @FXML
    private Text groupIDLabel;

    @FXML
    private Text groupRoleLabel;

    @FXML
    private AnchorPane anchorPane;
    //User story table
    @FXML
    private TableView<UserStory> sprintBacklogTableView;

    @FXML
    private TableColumn<Integer, UserStory> usNumberColumn;

    @FXML
    private TableColumn<String, UserStory> userStoryColumn;

    @FXML
    private TableColumn<Boolean, UserStory> activeColumn;

    @FXML
    private TableColumn<Integer, UserStory> storyPointsColumn;

    @FXML
    private TableColumn<Integer, UserStory> completedColumn;

    //Task table
    @FXML
    private TableView<Task> taskTableView;
    @FXML
    private TableColumn<Integer, Task> taskNumberColumn;
    @FXML
    private TableColumn<String, Task> taskMessageColumn;
    @FXML
    private TableColumn<Integer, Task> taskStoryPointsColumn;
    @FXML
    private TableColumn<Boolean, Task> taskCompletedColumn;
    @FXML
    private ChoiceBox<Integer> sprintNumberChoiceBox;

    //Add new task
    @FXML
    private TextField taskTextField;
    @FXML
    private TextField storyPointsField;


    private SprintBacklogVM sprintBacklogVM;

    public SprintBacklogView() {

    }

    public void init(SprintBacklogVM sprintBacklogVM) throws RemoteException {
        this.sprintBacklogVM = sprintBacklogVM;
        usernameLabel.textProperty().bindBidirectional(sprintBacklogVM.getUsername());
        groupIDLabel.textProperty().bindBidirectional(sprintBacklogVM.getGroupID());
        groupRoleLabel.textProperty().bindBidirectional(sprintBacklogVM.getGroupRole());
        taskTextField.textProperty().bindBidirectional(sprintBacklogVM.getTaskFieldProperty());


        for (int i = 1; i <=sprintBacklogVM.getNumberOfSprints(); i++) {
            sprintNumberChoiceBox.getItems().add(i);
        }

       sprintBacklogTableView.setItems(sprintBacklogVM.getUserStories());


        usNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Integer, UserStory>, ObservableValue<UserStory>>() {
            @Override
            public ObservableValue<UserStory> call(TableColumn.CellDataFeatures<Integer, UserStory> param) {
                return new ReadOnlyObjectWrapper(sprintBacklogTableView.getItems().indexOf(param.getValue()));
            }
        });
        sprintBacklogTableView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isControlDown()) {
                    return;
                }

                if (sprintBacklogTableView.getEditingCell() == null) {
                    sprintBacklogTableView.getSelectionModel().clearSelection();
                }
            }

        });
        userStoryColumn.setCellValueFactory(new PropertyValueFactory<>("userStory"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("currentlyActive"));
        storyPointsColumn.setCellValueFactory(new PropertyValueFactory<>("storyPoint"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
//Tasks
        taskTextField.textProperty().bindBidirectional(sprintBacklogVM.getTaskFieldProperty());
        storyPointsField.textProperty().bindBidirectional(sprintBacklogVM.getStoryPointsFieldProperty());

        taskTableView.setItems(sprintBacklogVM.getTasks());

        taskNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Integer, Task>, ObservableValue<Task>>() {
            @Override
            public ObservableValue<Task> call(TableColumn.CellDataFeatures<Integer, Task> param) {
                return new ReadOnlyObjectWrapper(taskTableView.getItems().indexOf(param.getValue()));
            }
        });

        taskMessageColumn.setCellValueFactory(new PropertyValueFactory<>("taskMessage"));
        taskStoryPointsColumn.setCellValueFactory(new PropertyValueFactory<>("storyPoints"));
        taskCompletedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));

        sprintBacklogVM.refreshView();


    }

    @FXML
    void onRemoveFromSprintBacklogClicked(ActionEvent event) throws RemoteException {
        UserStory userStory = sprintBacklogTableView.getSelectionModel().getSelectedItem();
        sprintBacklogVM.sendToProductBacklog(userStory);

    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        sprintBacklogVM.openDashboardView();
    }

    @FXML
    void onGroupManagementClicked(ActionEvent event) throws RemoteException {
        sprintBacklogVM.openGroupManagementView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onProductBacklogClicked(ActionEvent event) throws RemoteException {
        sprintBacklogVM.openProductBacklogView();
    }

    @FXML
    public void onLoadSprint(ActionEvent actionEvent) throws RemoteException {
        sprintBacklogVM.loadSprint(sprintNumberChoiceBox.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onAddTaskClicked(ActionEvent actionEvent) throws RemoteException {


        sprintBacklogVM.addTask(sprintBacklogTableView.getSelectionModel().getSelectedItem().getStoryId());
    }

    @FXML
    public void onLoadTasksClicked(ActionEvent actionEvent) throws RemoteException {
        sprintBacklogVM.loadTasks(sprintBacklogTableView.getSelectionModel().getSelectedItem().getStoryId());
    }

    @FXML
    public void onMeetingNote(ActionEvent actionEvent){
        sprintBacklogVM.getMeetings();
    }

    @FXML
    public void onSprintManagementClicked(ActionEvent actionEvent) {
        sprintBacklogVM.openSprintMagagmentView();
    }
}


