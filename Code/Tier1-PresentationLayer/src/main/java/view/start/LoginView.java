package view.start;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.start.LoginVM;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;


public class LoginView {

@FXML
    private TextField usernameTxtField;

@FXML
    private PasswordField passwordTxtField;

@FXML
    private AnchorPane anchorPane;

@FXML
private void onLoginClick() throws RemoteException {
    loginVM.logIn();
}

@FXML
private void onRegisterClick(){
    loginVM.openRegisterView();

}

private LoginVM loginVM;

public LoginView(){

}

public void init(LoginVM loginVM){
    this.loginVM = loginVM;
    usernameTxtField.textProperty().bindBidirectional(loginVM.getUsername());
    passwordTxtField.textProperty().bindBidirectional(loginVM.getPassword());
}

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }


}
