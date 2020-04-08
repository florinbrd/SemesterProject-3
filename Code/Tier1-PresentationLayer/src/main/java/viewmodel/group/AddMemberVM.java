package viewmodel.group;
import javaModel.Group;
import javaModel.GroupMember;
import javaModel.IDataModel;
import java.*;
import javaModel.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import view.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class AddMemberVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private ObservableList<String> users;
    private StringProperty groupRole;
    private StringProperty username;
    private StringProperty groupID;

    public AddMemberVM(IDataModel dataModel, ViewHandler viewHandler){
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        users = FXCollections.observableArrayList();
        groupRole = new SimpleStringProperty(dataModel.getSession().getGroupRole());
        username = new SimpleStringProperty(dataModel.getSession().getUsername());
        groupID = new SimpleStringProperty(String.valueOf(dataModel.getSession().getGroupId()));
        dataModel.addListener("GetAvailableUsersFromServer", this::loadUsers);
    }

    private void loadUsers(PropertyChangeEvent evt) {
        ArrayList<String> usersList = (ArrayList<String>) evt.getNewValue();
        users.addAll(usersList);
    }

    public ObservableList<String> getUsers(){
        return users;
    }

    public void addGroupMember(String u, String role) throws RemoteException {
        User user = new User();
        user.setUsername(u);
        GroupMember member = new GroupMember(user, role);
        member.setGroupID(Integer.parseInt(groupID.getValue()));
        boolean confirmation = dataModel.addGroupMember(member);
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

    public StringProperty getGroupRole() { return groupRole; }
    public StringProperty getUsername() { return  username; }
    public StringProperty getGroupID() { return groupID; }


    public void openDashboardView() {
        viewHandler.openHomepageView();
    }

    public void openGroupManagementView() throws RemoteException {
        viewHandler.openGroupManagementView();
    }

    public void openProductBacklogView() throws RemoteException {
        viewHandler.openProductBacklogView();
    }


    public void openSprintManagementView() {
        viewHandler.openSprintManagementView();
    }

    public void loadAllUsernames() throws RemoteException {
        users.setAll(dataModel.loadAllUsernames());
    }

    public void refreshView() {
        groupID.setValue(""+dataModel.getSession().getGroupId());
    }

}