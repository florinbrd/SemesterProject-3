package viewmodel.productbacklog;

import javaModel.IDataModel;
import javaModel.ProductBacklog;
import javaModel.UserStory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import view.ViewHandler;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;

public class ProductBacklogVM {
    private StringProperty username;
    private StringProperty groupID;
    private StringProperty groupRole;
    private IDataModel datamodel;
    private ViewHandler viewHandler;
    private ObservableList<UserStory> userStories;
    private StringProperty story;
    private StringProperty storyPoints;

    public ProductBacklogVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.datamodel = dataModel;
        this.viewHandler = viewHandler;
        username = new SimpleStringProperty(dataModel.getSession().getUsername());
        groupID = new SimpleStringProperty(String.valueOf(dataModel.getSession().getGroupId()));
        userStories = FXCollections.observableArrayList();
        groupRole = new SimpleStringProperty(dataModel.getSession().getGroupRole());
        dataModel.addListener("GetProductBacklogFromServer", this::loadUserStories);
        story = new SimpleStringProperty();
        storyPoints = new SimpleStringProperty();
    }

    private void loadUserStories(PropertyChangeEvent evt) {
        userStories.removeAll();
        ProductBacklog productBacklog = (ProductBacklog) evt.getNewValue();
        userStories.setAll(productBacklog.getUserStories());

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

    public void AddUserStory() throws RemoteException {
        UserStory storyItem = new UserStory(story.getValue(), Integer.parseInt(storyPoints.getValue()), 0);
        storyItem.setCurrentlyActive(true);
        boolean confimation = datamodel.addUserStory(storyItem);
        if(confimation)
        {
            userStories.add(storyItem);
        }
        else
        {
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

    public StringProperty getStory() {
        return story;
    }

    public StringProperty getStoryPoints() {
        return storyPoints;
    }

    public void openSprintManagementView() {
        viewHandler.openSprintManagementView();
    }

    public void loadbacklog() throws RemoteException {

        ProductBacklog productBacklog = datamodel.loadProductBacklog();
        if (productBacklog!=null)
        {
            userStories.setAll(productBacklog.getUserStories());
        }
    }

    public void refreshView() {
        groupID.setValue(""+datamodel.getSession().getGroupId());
    }

    public boolean AddUserStoryToSprint(UserStory selectedStory) throws RemoteException {
        boolean confirmation = datamodel.AddUserStoryToSprint(selectedStory);

        if(!confirmation)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Error");
            errorAlert.showAndWait();
        }
        return confirmation;
    }
}
