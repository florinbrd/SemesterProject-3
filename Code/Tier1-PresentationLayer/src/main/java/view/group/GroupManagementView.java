package view.group;

import javaModel.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import viewmodel.group.GroupManagementVM;

import java.rmi.RemoteException;

public class GroupManagementView {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Text usernameLabel;

    @FXML
    private Text groupIDLabel;

    @FXML
    private Text groupRoleLabel;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    @FXML
    private TableView<GroupMember> memberTableVIew;

    @FXML
    private TableColumn<GroupMember, String> usernameCol;

    @FXML
    private TableColumn<GroupMember, String> roleCol;


    private GroupManagementVM groupManagementVM;
    private GroupMember selectedGroupMember;

    public void init(GroupManagementVM groupManagementVM) throws RemoteException {
        this.groupManagementVM = groupManagementVM;
        groupManagementVM.loadGroup();
        memberTableVIew.setItems(groupManagementVM.getGroupMembers());
        usernameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GroupMember, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<GroupMember, String> data){
                return new SimpleStringProperty(data.getValue().user.getUsername());
            }
        });
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleChoiceBox.getItems().add("Developer");
        roleChoiceBox.getItems().add("Scrum Master");
        roleChoiceBox.getItems().add("Product Owner");
        usernameLabel.textProperty().bindBidirectional(groupManagementVM.getUsername());
        groupIDLabel.textProperty().bindBidirectional(groupManagementVM.getGroupID());
        groupRoleLabel.textProperty().bindBidirectional(groupManagementVM.getGroupRole());
        roleChoiceBox.valueProperty().bindBidirectional(groupManagementVM.changedGroupRoleProperty());
        groupManagementVM.refreshView();
    }

    @FXML
    void onRemoveMemberClicked(ActionEvent event) throws RemoteException {

        selectedGroupMember = memberTableVIew.getSelectionModel().getSelectedItem();
        boolean confirmation = groupManagementVM.removeMember(selectedGroupMember);
        if(confirmation)
        {
            memberTableVIew.getItems().remove(selectedGroupMember);
        }
    }

    @FXML
    void onChangeRoleClicked(ActionEvent event) throws RemoteException {
        selectedGroupMember = memberTableVIew.getSelectionModel().getSelectedItem();
        groupManagementVM.changeRole(selectedGroupMember);
        memberTableVIew.refresh();
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        groupManagementVM.openDashBoardView();
    }

    @FXML
    void onGroupManagementClicked(ActionEvent event) throws RemoteException {
        groupManagementVM.openGroupManagementView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onProductBacklogClicked(ActionEvent event) throws RemoteException {
        groupManagementVM.openProductBacklogView();
    }

    @FXML
    void onAddMemberClicked(ActionEvent event) throws RemoteException {
        groupManagementVM.openAddMemberView();
    }

    @FXML
    void onSprintManagementClicked(ActionEvent event) {
        groupManagementVM.openSprintManagementView();
    }


}
