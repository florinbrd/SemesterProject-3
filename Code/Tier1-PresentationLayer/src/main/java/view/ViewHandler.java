package view;

import view.group.AddMemberView;
import view.group.CreateGroupView;
import view.sprints.MeetingNotesView;
import view.sprints.SprintBacklogView;
import view.sprints.SprintManagementView;
import viewmodel.ViewModelProvider;
import view.group.GroupManagementView;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.productbacklog.ProductBacklogView;
import view.start.HomepageView;
import view.start.LoginView;
import view.start.RegisterView;

import java.io.IOException;
import java.rmi.RemoteException;

public class ViewHandler {

    private Stage primaryStage;
    private ViewModelProvider viewModelProvider;
    private Rectangle2D screensize;

    public ViewHandler(Stage primaryStage, ViewModelProvider viewModelProvider){
        this.primaryStage = primaryStage;
        this.viewModelProvider = viewModelProvider;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        screensize = Screen.getPrimary().getVisualBounds();
        viewModelProvider.instantiateViewModels(this);

    }

    public void start() {
     openRegisterView();
    }


    public void openRegisterView()
    {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/Register.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        RegisterView view = loader.getController();
        view.init(viewModelProvider.getRegisterVM());

        Scene scene = new Scene(root, 1100, 700);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openLoginView(){
        {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/fxmls/Login.fxml"));
            Parent root = null;
            try{
                root = loader.load();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            LoginView view = loader.getController();
            view.init(viewModelProvider.getLogInVM());

            Scene scene = new Scene(root, 1100, 700);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();

        }
    }

    public void openHomepageView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/Homepage.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        HomepageView view = loader.getController();
        view.init(viewModelProvider.getHomepageVM());

        Scene scene = new Scene(root, 1100, 700);
        primaryStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth()-1100)/2);
        primaryStage.setY((screenBounds.getHeight()-700)/2);
        primaryStage.show();
    }

    public void openGroupManagementView() throws RemoteException {
       FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/GroupManagement.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        GroupManagementView view = loader.getController();
        view.init(viewModelProvider.getGroupManagementVM());

        Scene scene = new Scene(root, screensize.getWidth(), screensize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
    }

    public void openProductBacklogView() throws RemoteException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/ProductBacklog.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ProductBacklogView view = loader.getController();
        view.init(viewModelProvider.getProductBacklogVM());

        Scene scene = new Scene(root, screensize.getWidth(), screensize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
    }


    public void openAddMemberView() throws RemoteException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/AddMember.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        AddMemberView view = loader.getController();
        view.init(viewModelProvider.getAddMemberVM());

        Scene scene = new Scene(root, screensize.getWidth(), screensize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openCreateGroupView() throws RemoteException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/CreateGroup.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        CreateGroupView view = loader.getController();
        view.init(viewModelProvider.getCreateGroupVM());

        Scene scene = new Scene(root, screensize.getWidth(), screensize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openSprintManagementView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/SprintManagement.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        SprintManagementView view = loader.getController();
        view.init(viewModelProvider.getSprintManagementVM());

        Scene scene = new Scene(root, screensize.getWidth(), screensize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
    }

    public void openMeetingNotesView() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/MeetingNotes.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        MeetingNotesView view = loader.getController();
        view.init(viewModelProvider.getMeetingNotesVM());

        Scene scene = new Scene(root, screensize.getWidth(), screensize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void openSprintBacklogView() throws RemoteException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxmls/SprintBacklog.fxml"));
        Parent root = null;
        try{
            root = loader.load();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        SprintBacklogView view = loader.getController();
        view.init(viewModelProvider.getSprintBacklogVM());

        Scene scene = new Scene(root, screensize.getWidth(), screensize.getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
