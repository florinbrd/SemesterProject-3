package viewmodel.group;


import javaModel.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.ViewHandler;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class GroupManagementVM {

    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private ObservableList<GroupMember> groupMembers;
    private StringProperty username;
    private StringProperty groupID;
    private StringProperty groupRole;

    private StringProperty changedGroupRole;

    public GroupManagementVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        groupMembers = FXCollections.observableArrayList();
        groupID = new SimpleStringProperty(String.valueOf(dataModel.getSession().getGroupId()));
        username = new SimpleStringProperty(dataModel.getSession().getUsername());
        groupRole = new SimpleStringProperty(dataModel.getSession().getGroupRole());
        changedGroupRole = new SimpleStringProperty();
        dataModel.addListener("GetRegisteredGroupMembersFromServer", this::loadGroupMembers);

    }

    private void loadGroupMembers(PropertyChangeEvent evt) {
        ArrayList<GroupMember> membersList = (ArrayList<GroupMember>) evt.getNewValue();
        groupMembers.addAll(membersList);
    }

    public ObservableList<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public boolean removeMember(GroupMember e) throws RemoteException {
        return dataModel.removeGroupMember(e);
    }

    public void openAddMemberView() throws RemoteException {
        viewHandler.openAddMemberView();
    }


    public StringProperty getGroupRole() {
        return groupRole;
    }

    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getGroupID() {
        return groupID;
    }

    public StringProperty changedGroupRoleProperty() {
        return changedGroupRole;
    }

    public void openDashBoardView() {
        viewHandler.openHomepageView();
    }

    public void openGroupManagementView() throws RemoteException {
        viewHandler.openGroupManagementView();
    }

    public void openProductBacklogView() throws RemoteException {
        viewHandler.openProductBacklogView();
    }


    public void changeRole(GroupMember selectedGroupMember) throws RemoteException {

        selectedGroupMember.setRole(changedGroupRole.getValue());

        boolean confirmation = dataModel.changeRole(selectedGroupMember);

        if (confirmation) {
            groupMembers.remove(selectedGroupMember);
            GroupMember confirm = selectedGroupMember;
            groupMembers.add(confirm);

        }
    }

    public void openSprintManagementView() {
        viewHandler.openSprintManagementView();
    }

    public void loadGroup() throws RemoteException {
        groupMembers.setAll(dataModel.loadGroup().groupMembers);
    }

    public void refreshView() {
        groupID.setValue(""+dataModel.getSession().getGroupId());
    }
}
