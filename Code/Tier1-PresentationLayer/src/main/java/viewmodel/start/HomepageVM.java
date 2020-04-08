package viewmodel.start;

import javaModel.IDataModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import view.ViewHandler;

import java.rmi.RemoteException;

public class HomepageVM {
    private StringProperty username;
    private IDataModel datamodel;
    private ViewHandler viewHandler;

    public HomepageVM(IDataModel dataModel, ViewHandler viewHandler){
        this.datamodel = dataModel;
        this.viewHandler = viewHandler;
        username = new SimpleStringProperty();
    }

    public void openGroupManagementView() throws RemoteException {
        viewHandler.openGroupManagementView();
    }

    public void openProductBacklogView() throws RemoteException {
        viewHandler.openProductBacklogView();
    }

    public StringProperty getUsername() {
        return username;
    }

    public void openSprintManagementView() {
        viewHandler.openSprintManagementView();
    }

    public void openCreateGroupView() throws RemoteException {
        viewHandler.openCreateGroupView();
    }

    public boolean isGroupMember()
    {
        return datamodel.getSession().getGroupId() != 0;
    }

    public void setUsername() {
        username.setValue(datamodel.getSession().getUsername() + ",");
    }
}
