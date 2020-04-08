package view.group;

import javaModel.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewmodel.group.AddMemberVM;

import java.rmi.RemoteException;

public class AddMemberView {

    @FXML
    private Text usernameLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView<String> userListView;

    @FXML
    private Text groupIDLabel;

    @FXML
    private Text groupRoleLabel;

    @FXML
    private ChoiceBox<String> groupRoleChoiceBox;

    private User selectedUser;
    private AddMemberVM addMemberVM;

    public void init(AddMemberVM addMemberVM) throws RemoteException {
        this.addMemberVM = addMemberVM;
        addMemberVM.loadAllUsernames();
        userListView.setItems(addMemberVM.getUsers());
        groupRoleChoiceBox.getItems().add("Scrum Master");
        groupRoleChoiceBox.getItems().add("Product Owner");
        groupRoleChoiceBox.getItems().add("Developer");
        groupIDLabel.textProperty().bindBidirectional(addMemberVM.getGroupID());
        usernameLabel.textProperty().bindBidirectional(addMemberVM.getUsername());
        groupRoleLabel.textProperty().bindBidirectional(addMemberVM.getGroupRole());
        addMemberVM.refreshView();
    }

    @FXML
    void onAddMemberClicked(ActionEvent event) throws RemoteException {
        String roleChoiceBoxValue = groupRoleChoiceBox.getValue();
        String selectedUser = userListView.getSelectionModel().getSelectedItem();
        addMemberVM.addGroupMember(selectedUser, roleChoiceBoxValue);
    }



    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        addMemberVM.openDashboardView();
    }

    @FXML
    void onGroupManagementClicked(ActionEvent event) throws RemoteException {
        addMemberVM.openGroupManagementView();
    }

    @FXML
    void onProductBacklogClicked(ActionEvent event) throws RemoteException { addMemberVM.openProductBacklogView();}

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML void onSprintManagementClicked(ActionEvent event)
    {
        addMemberVM.openSprintManagementView();
    }
}



