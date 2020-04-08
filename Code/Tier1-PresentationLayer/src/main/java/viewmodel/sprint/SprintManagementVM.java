package viewmodel.sprint;

import javaModel.IDataModel;
import javaModel.SprintList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import view.ViewHandler;

import java.rmi.RemoteException;

public class SprintManagementVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private StringProperty username;
    private StringProperty groupID;
    private StringProperty groupRole;
    private StringProperty numberOfSprints;
    private StringProperty durationOfSprints;
    private StringProperty activeSprint;

    public SprintManagementVM(IDataModel dataModel, ViewHandler viewHandler){
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        groupID = new SimpleStringProperty(String.valueOf(dataModel.getSession().getGroupId()));
        username = new SimpleStringProperty(dataModel.getSession().getUsername());
        groupRole = new SimpleStringProperty(dataModel.getSession().getGroupRole());
        numberOfSprints = new SimpleStringProperty();
        durationOfSprints = new SimpleStringProperty();
        activeSprint = new SimpleStringProperty();
    }

    public void setNumberOfSprints() throws RemoteException {
        SprintList sprintList = new SprintList();
        sprintList.numberOfSprints = Integer.parseInt(numberOfSprints.getValue());
        sprintList.groupID = Integer.parseInt(groupID.getValue());
        boolean confirmation = dataModel.setNumberOfSprints(sprintList);
        if(!confirmation)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Error");
            errorAlert.showAndWait();
        }
    }

    public void setDurationOfSprints() throws RemoteException {
        SprintList sprintList = new SprintList();
        sprintList.duration = Integer.parseInt(durationOfSprints.getValue());
        sprintList.groupID = Integer.parseInt(groupID.getValue());
        boolean confirmation = dataModel.setDurationOfSprints(sprintList);
        if(!confirmation)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Error");
            errorAlert.showAndWait();
        }
    }

    public void openDashBoardView() { viewHandler.openHomepageView();}
    public void openGroupManagementView() throws RemoteException { viewHandler.openGroupManagementView();}
    public void openProductBacklogView() throws RemoteException { viewHandler.openProductBacklogView();}
    public StringProperty groupIDProperty() {
        return groupID;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty groupRoleProperty() {
        return groupRole;
    }

    public StringProperty numberOfSprintsProperty() {
        return numberOfSprints;
    }

    public StringProperty durationOfSprintsProperty() {
        return durationOfSprints;
    }

    public StringProperty activeSprintProperty() {
        return activeSprint;
    }

    public void setActiveSprint() throws RemoteException {
        SprintList sprintList = new SprintList();
        sprintList.currentlyActiveSprint = Integer.parseInt(activeSprint.getValue());
        sprintList.groupID = Integer.parseInt(groupID.getValue());
        boolean confirmation = dataModel.setActiveSprint(sprintList);
        if(!confirmation)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Error");
            errorAlert.showAndWait();
        }
    }

    public void openSprintManagementView() {
        viewHandler.openSprintManagementView();
    }

    public void openSprintBacklogView() throws RemoteException {
        viewHandler.openSprintBacklogView();
    }

    public void refreshView() {
        groupID.setValue(""+dataModel.getSession().getGroupId());
    }
}
