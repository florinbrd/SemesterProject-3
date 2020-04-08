package view.start;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viewmodel.start.RegisterVM;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class RegisterView {

    private RegisterVM registerVM;


@FXML
    private TextField userNameCreateAccount;

@FXML
    private PasswordField passwordCreateAccount;

@FXML
    private PasswordField confirmPasswordCreateAccount;

@FXML private AnchorPane anchorPane;


@FXML
    private void onLoginClick(){
    registerVM.openLoginView();
}

@FXML
    private void onRegisterClick() throws RemoteException {
    registerVM.createAccount();


}

    public void init(RegisterVM registerVM){
        this.registerVM = registerVM;
        userNameCreateAccount.textProperty().bindBidirectional(registerVM.getUsername());
        passwordCreateAccount.textProperty().bindBidirectional(registerVM.getPassword());
        confirmPasswordCreateAccount.textProperty().bindBidirectional(registerVM.getPasswordConfirm());
    }

    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }
}
