package viewmodel.sprint;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import javaModel.*;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import view.ViewHandler;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SprintBacklogVM {
    private ViewHandler viewHandler;
    private IDataModel datamodel;

    //UserStory table
    private StringProperty username;
    private StringProperty groupID;
    private StringProperty groupRole;
    private ObservableList<UserStory> userStories;

    //Task table
    private StringProperty storyPointsField;
    private StringProperty taskField;
    private ObservableList<Task> tasks;
    private int lastTaskUserStoryIdLoad;


    public StringProperty getStoryPointsFieldProperty() {
        return storyPointsField;
    }

    public void setStoryPointsField(String storyPointsField) {
        this.storyPointsField.set(storyPointsField);
    }


    public StringProperty getTaskFieldProperty() {
        return taskField;
    }

    public void setTaskField(String taskField) {
        this.taskField.set(taskField);
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public SprintBacklogVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.datamodel = dataModel;
        this.viewHandler = viewHandler;
        username = new SimpleStringProperty(dataModel.getSession().getUsername());
//        username = new SimpleStringProperty("Jamie");
        groupID = new SimpleStringProperty(String.valueOf(dataModel.getSession().getGroupId()));
        groupRole = new SimpleStringProperty(dataModel.getSession().getGroupRole());
        storyPointsField = new SimpleStringProperty();
        taskField = new SimpleStringProperty();
        userStories = FXCollections.observableArrayList();
        tasks = FXCollections.observableArrayList();

        dataModel.addListener("GetProductBacklogFromServer", this::loadUserStories);
    }

    public void loadUserStories(PropertyChangeEvent evt) {
        Sprint sprintList = (Sprint) evt.getNewValue();
        ArrayList<UserStory> sprintBacklogStories = sprintList.getUserStories();
        userStories.addAll(sprintBacklogStories);
    }

    public void loadTasks(int storyId) throws RemoteException {

        UserStory userStory = datamodel.loadTasks(storyId);
        tasks.removeAll();
        tasks.setAll(userStory.getTaskList());
    }


    public void openDashboardView() {
        viewHandler.openHomepageView();
    }

    public void openGroupManagementView() throws RemoteException {
        viewHandler.openGroupManagementView();
    }

    public void openProductBacklogView() throws RemoteException {
        viewHandler.openProductBacklogView();
    }

    public void sendToProductBacklog(UserStory userStory) throws RemoteException {
        boolean confirmation;
        confirmation = datamodel.removeUserStoryFromSprint(userStory);

        if (confirmation) {
            userStories.remove(userStory);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Error");
            errorAlert.showAndWait();

        }
    }


    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getGroupID() {
        return groupID;
    }

    public StringProperty getGroupRole() {
        return groupRole;
    }

    public ObservableList<UserStory> getUserStories() {
        return userStories;
    }

    public void addTask(int storyId) throws RemoteException {

        Task task = new Task(taskField.getValue(), Integer.parseInt(storyPointsField.getValue()), lastTaskUserStoryIdLoad);
        task.setStoryId(storyId);

        boolean confirmation = datamodel.AddTasksToUserStories(task);

        if (confirmation) {
            tasks.add(task);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Error");
            errorAlert.showAndWait();

        }

    }

    public int getNumberOfSprints() throws RemoteException {
        return datamodel.getNumberOfSprints();
    }

    public void getMeetings() {
        viewHandler.openMeetingNotesView();
    }

    public void refreshView() {
        groupID.setValue("" + datamodel.getSession().getGroupId());
    }

    public void openSprintMagagmentView() {
        viewHandler.openSprintManagementView();
    }

    public void loadSprint(int sprintNumber) throws RemoteException {
        Sprint sprint = datamodel.loadSprint(sprintNumber);
        if (sprint != null) {
            userStories.setAll(sprint.getUserStories());
            System.out.println("SPRINT IS NOT NULL----------------------------------------");
        } else {
            userStories.removeAll();
            refreshView();
            System.out.println("SPRINT IS NULL----------------------------------------");
        }


    }
}
