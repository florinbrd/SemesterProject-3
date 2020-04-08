package view.group;

import javaModel.GroupMember;
import javaModel.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import viewmodel.group.CreateGroupVM;

import java.rmi.RemoteException;

public class CreateGroupView {


    @FXML
    private AnchorPane anchorPane;


    @FXML
    private Text usernameLabel;

    @FXML
    private ListView<String> userListView;

    @FXML
    private TableView<GroupMember> groupMemberTableView;

    @FXML
    private TableColumn<GroupMember, String> groupMemberColumn;

    @FXML
    private TableColumn<String, GroupMember> groupRoleColumn;

    @FXML
    private ChoiceBox<String> groupRoleChoiceBox;

    private CreateGroupVM createGroupVM;

    public void init(CreateGroupVM createGroupVM) throws RemoteException {
        this.createGroupVM = createGroupVM;
        createGroupVM.loadAllUsernames();
        userListView.setItems(createGroupVM.getUsers());
        groupMemberTableView.setItems(createGroupVM.getGroupMembers());
        groupMemberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GroupMember, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<GroupMember, String> param) {
                return new SimpleStringProperty(param.getValue().getUser().getUsername());
            }
        });
        groupRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        usernameLabel.textProperty().bindBidirectional(createGroupVM.getUsername());
        groupRoleChoiceBox.getItems().add("Developer");
        groupRoleChoiceBox.getItems().add("Scrum Master");
        groupRoleChoiceBox.getItems().add("Product Owner");

    }

    @FXML
    void onAddMemberClicked(ActionEvent event) {
        String selectedUser = userListView.getSelectionModel().getSelectedItem();
        String selectedRole = groupRoleChoiceBox.getSelectionModel().getSelectedItem();
        User user = new User();
        user.setUsername(selectedUser);
        createGroupVM.addMember(user, selectedRole);
        userListView.getItems().remove(selectedUser);
    }


    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onCreateGroupClicked(ActionEvent event) throws RemoteException {
        createGroupVM.createGroup();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        createGroupVM.openDashboardView();
    }

    @FXML
    void onGroupManagementClicked(ActionEvent event) throws RemoteException {
        createGroupVM.openGroupManagementView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onProductBacklogClicked(ActionEvent event) throws RemoteException {
        createGroupVM.openProductBacklogView();
    }

    @FXML
    void onSprintManagementClicked(ActionEvent event)
    {
        createGroupVM.openSprintManagementView();
    }

}
