package view.productbacklog;

import viewmodel.productbacklog.ProductBacklogVM;
import javaModel.UserStory;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.rmi.RemoteException;

public class ProductBacklogView {
    @FXML
    private Text usernameLabel;

    @FXML
    private Text groupIDLabel;

    @FXML
    private Text groupRoleLabel;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<UserStory> productBacklogTableView;

    @FXML
    private TableColumn<Integer, UserStory> usNumberColumn;

    @FXML
    private TableColumn<String, UserStory> userStoryColumn;

    @FXML
    private TableColumn<Integer, UserStory> storyPointsColumn;

    @FXML
    private TableColumn<Integer, UserStory> completedColumn;

    @FXML
    private TextField storyTxtField;

    @FXML
    private TextField storyPointsTxtField;

    private ProductBacklogVM productBacklogVM;

    public ProductBacklogView() {

    }

    public void init(ProductBacklogVM productBacklogVM) throws RemoteException {
        this.productBacklogVM = productBacklogVM;
        productBacklogVM.loadbacklog();
        usernameLabel.textProperty().bindBidirectional(productBacklogVM.getUsername());
        groupIDLabel.textProperty().bindBidirectional(productBacklogVM.getGroupID());
        groupRoleLabel.textProperty().bindBidirectional(productBacklogVM.getGroupRole());
        productBacklogTableView.setItems(productBacklogVM.getUserStories());
        usNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Integer, UserStory>, ObservableValue<UserStory>>() {
            @Override
            public ObservableValue<UserStory> call(TableColumn.CellDataFeatures<Integer, UserStory> param) {
                return new ReadOnlyObjectWrapper(productBacklogTableView.getItems().indexOf(param.getValue()));
            }
        });
        userStoryColumn.setCellValueFactory(new PropertyValueFactory<>("userStory"));
        storyPointsColumn.setCellValueFactory(new PropertyValueFactory<>("storyPoint"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
        storyTxtField.textProperty().bindBidirectional(productBacklogVM.getStory());
        storyPointsTxtField.textProperty().bindBidirectional(productBacklogVM.getStoryPoints());
        productBacklogVM.refreshView();

    }

    @FXML
    void onAddUserStoryClicked(ActionEvent event) throws RemoteException {
        productBacklogVM.AddUserStory();
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        productBacklogVM.openDashboardView();
    }

    @FXML
    void onGroupManagementClicked(ActionEvent event) throws RemoteException {
        productBacklogVM.openGroupManagementView();
    }

    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onProductBacklogClicked(ActionEvent event) throws RemoteException {
        productBacklogVM.openProductBacklogView();
    }

    @FXML
    void onSprintManagementClicked(ActionEvent event) {
        productBacklogVM.openSprintManagementView();
    }

    @FXML
    void onAddStoryToSprintClicked(ActionEvent event) throws RemoteException {
        UserStory selectedStory = productBacklogTableView.getSelectionModel().getSelectedItem();
        boolean confirmation = productBacklogVM.AddUserStoryToSprint(selectedStory);
        if (confirmation) {
            productBacklogTableView.getItems().remove(selectedStory);
        }
    }


}


