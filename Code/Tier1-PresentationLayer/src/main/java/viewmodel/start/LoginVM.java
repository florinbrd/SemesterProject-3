package viewmodel.start;

import javaModel.IDataModel;
import javaModel.Login;
import javaModel.Session;
import javafx.scene.control.Alert;
import view.ViewHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class LoginVM {

    private StringProperty username;
    private StringProperty password;
    private IDataModel datamodel;
    private ViewHandler viewHandler;

    public LoginVM(IDataModel dataModel, ViewHandler viewHandler) {
        this.datamodel = dataModel;
        this.viewHandler = viewHandler;
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    public void logIn() throws RemoteException {
        Login login = new Login(username.getValue(), password.getValue());

        Session session =  datamodel.login(login);
        boolean success = session.isValidated();
        System.out.println("sesion validated??: "+session.getGroupId());
        if (success) {

            datamodel.setSession(session);
            System.out.println("sesion validated groupid: "+session.getGroupId());
            viewHandler.openHomepageView();

        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Input not valid");
            errorAlert.showAndWait();
        }
    }

    public void openRegisterView() {
        viewHandler.openRegisterView();
    }

    public StringProperty getUsername() {
        return username;
    }

    public StringProperty getPassword() {
        return password;
    }
}
