package viewmodel.group;

import javaModel.Group;
import javaModel.GroupMember;
import javaModel.IDataModel;
import javaModel.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import view.ViewHandler;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CreateGroupVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private ObservableList<String> users;
    private ObservableList<GroupMember> groupMembers;
    private StringProperty username;

    public CreateGroupVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        users = FXCollections.observableArrayList();
        groupMembers = FXCollections.observableArrayList();
        username = new SimpleStringProperty(dataModel.getSession().getUsername());

        dataModel.addListener("GetAvailableUsersFromServer", this::loadUsers);
    }

    private void loadUsers(PropertyChangeEvent evt) {
        ArrayList<String> usersList = (ArrayList<String>) evt.getNewValue();
        users.addAll(usersList);
    }

    public ObservableList<String> getUsers() {
        return users;
    }

    public ObservableList<GroupMember> getGroupMembers() {
        return groupMembers;
    }


    public void addMember(User selectedUser, String selectedRole) {
        GroupMember groupMember = new GroupMember(selectedUser, selectedRole);
        groupMember.setGroupID(dataModel.getSession().getGroupId());
        groupMembers.add(groupMember);
    }

    public void openDashboardView() {
        viewHandler.openHomepageView();
    }

    public void onGroupManagementView() throws RemoteException {
        viewHandler.openGroupManagementView();
    }

    public void openProductBacklogView() throws RemoteException {
        viewHandler.openProductBacklogView();
    }

    public void createGroup() throws RemoteException {

        Group group = new Group();
        group.setGroupId(dataModel.getSession().getGroupId());
        for (int i = 0; i < groupMembers.size(); i++) {
            group.addMember(groupMembers.get(i));
        }
        boolean confirmation = dataModel.createGroup(group);
        if(!confirmation)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Error");
            errorAlert.showAndWait();
        }
        else{
            viewHandler.openGroupManagementView();
        }
    }


    public void openSprintManagementView() {
        viewHandler.openSprintManagementView();
    }

    public void openGroupManagementView() throws RemoteException {
        viewHandler.openGroupManagementView();
    }

    public StringProperty getUsername() {
        return username;
    }

    public void loadAllUsernames() throws RemoteException {
        users.setAll(dataModel.loadAllUsernames());
    }
}
