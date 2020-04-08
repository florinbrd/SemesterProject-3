package viewmodel.start;

import javaModel.IDataModel;
import javaModel.User;
import javaModel.UserStory;
import javafx.scene.control.Alert;
import view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;


public class RegisterVM {

    private StringProperty username;
    private StringProperty password;
    private StringProperty passwordConfirm;
    private IDataModel dataModel;
    private ViewHandler viewHandler;

    public RegisterVM(IDataModel dataModel, ViewHandler viewHandler)
    {
        this.dataModel = dataModel;
        this.viewHandler = viewHandler;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        passwordConfirm = new SimpleStringProperty();
    }

    public void createAccount() throws RemoteException {
        if (password.getValue().equals(passwordConfirm.getValue()))
        {
            User e = new User(username.getValue(), password.getValue());

            boolean confimation = dataModel.createAccount(e);
            if(confimation)
            {
                viewHandler.openLoginView();
            }
            else
            {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error");
                errorAlert.setContentText("Username already exists");
                errorAlert.showAndWait();
            }
        }
        else
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Passwords don't match");
            errorAlert.showAndWait();
        }

    }

    public void openLoginView(){
        viewHandler.openLoginView();
    }

   public StringProperty getUsername(){
        return username;
   }

    public StringProperty getPassword(){
        return password;
    }

    public StringProperty getPasswordConfirm(){
        return passwordConfirm;
    }

    public void getMeetings(){
        viewHandler.openMeetingNotesView();
    }


}
