package view.start;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import viewmodel.start.HomepageVM;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.rmi.RemoteException;

public class HomepageView {

    @FXML
    private Text usernameLabelLeft;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView groupIcon;

    @FXML
    private ImageView sprintIcon;

    @FXML
    private ImageView productBacklogIcon;

    @FXML
    private Text groupManagementLabel;

    @FXML
    private Text sprintManagementLabel;

    @FXML
    private Text productBacklogLabel;

    @FXML
    private Button createGroupButton;

    @FXML
    private Text createGroupLabel;

    @FXML
    private Pane productBacklogPane;

    @FXML
    private Pane sprintPane;

    @FXML
    private Pane groupPane;


    private HomepageVM homepageVM;

    public HomepageView()
    {

    }

    public void init(HomepageVM homepageVM){
        this.homepageVM = homepageVM;

        usernameLabelLeft.textProperty().bindBidirectional(homepageVM.getUsername());
        homepageVM.setUsername();
        if (!homepageVM.isGroupMember())
        {
            sprintIcon.setVisible(false);
            sprintPane.setVisible(false);
            groupIcon.setVisible(false);
            groupPane.setVisible(false);
            productBacklogIcon.setVisible(false);
            productBacklogPane.setVisible(false);
        }
        else {
            sprintIcon.setVisible(true);
            sprintPane.setVisible(true);
            groupIcon.setVisible(true);
            groupPane.setVisible(true);
            productBacklogIcon.setVisible(true);
            productBacklogPane.setVisible(true);
        }
        groupIcon.hoverProperty().addListener(((observable, oldValue, show) -> {
            if(show)
            {
                groupManagementLabel.setVisible(true);
            }
            else
            {
                groupManagementLabel.setVisible(false);
            }
        }));
        sprintIcon.hoverProperty().addListener(((observable, oldValue, show) -> {
            if(show)
            {
                sprintManagementLabel.setVisible(true);
            }
            else
            {
                sprintManagementLabel.setVisible(false);
            }
        }));
        productBacklogIcon.hoverProperty().addListener(((observable, oldValue, show) -> {
            if(show)
            {
                productBacklogLabel.setVisible(true);
            }
            else
            {
                productBacklogLabel.setVisible(false);
            }
        }));
        boolean isGroupMember = homepageVM.isGroupMember();
        if(isGroupMember)
        {
            createGroupLabel.setVisible(false);
            createGroupButton.setVisible(false);
        }
        else
        {
            createGroupButton.setVisible(true);
            createGroupLabel.setVisible(true);
        }
    }

    @FXML
    void onGroupManagementClicked(MouseEvent event) throws RemoteException {
        homepageVM.openGroupManagementView();
    }

    @FXML
    void onProductBacklogClicked(MouseEvent event) throws RemoteException {
        homepageVM.openProductBacklogView();
    }

    @FXML
    void onSprintManagementClicked(MouseEvent event) {
        homepageVM.openSprintManagementView();
    }
    @FXML
    void onMinimizeClicked(MouseEvent event) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void onCloseClicked(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void onCreateGroupClicked(ActionEvent event) throws RemoteException {
        homepageVM.openCreateGroupView();
    }

}
