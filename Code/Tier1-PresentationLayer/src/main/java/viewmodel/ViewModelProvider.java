package viewmodel;

import viewmodel.sprint.MeetingNotesVM;
import viewmodel.sprint.SprintBacklogVM;
import viewmodel.sprint.SprintManagementVM;
import viewmodel.group.AddMemberVM;
import viewmodel.group.CreateGroupVM;
import viewmodel.group.GroupManagementVM;
import viewmodel.productbacklog.ProductBacklogVM;
import viewmodel.start.HomepageVM;
import viewmodel.start.LoginVM;
import viewmodel.start.RegisterVM;
import javaModel.IDataModel;
import view.ViewHandler;

public class ViewModelProvider {

    private IDataModel dataModel;
    private ViewHandler viewHandler;
    private LoginVM loginVM;
    private RegisterVM registerVM;
    private HomepageVM homepageVM;
    private ProductBacklogVM productBacklogVM;
    private AddMemberVM addMemberVM;
    private CreateGroupVM createGroupVM;
    private GroupManagementVM groupManagementVM;
    private SprintManagementVM sprintManagementVM;
    private MeetingNotesVM meetingNotesVM;
    private SprintBacklogVM sprintBacklogVM;

    public ViewModelProvider(IDataModel dataModel) {
        this.dataModel = dataModel;
    }

    public LoginVM getLogInVM() {
        return loginVM;
    }

    public RegisterVM getRegisterVM() {
        return registerVM;
    }

    public HomepageVM getHomepageVM() {
        return homepageVM;
    }

    public ProductBacklogVM getProductBacklogVM() {
        return productBacklogVM;
    }

    public AddMemberVM getAddMemberVM() {
        return addMemberVM;
    }

    public CreateGroupVM getCreateGroupVM(){
        return createGroupVM;
    }

    public GroupManagementVM getGroupManagementVM() { return  groupManagementVM; }

    public SprintManagementVM getSprintManagementVM() { return  sprintManagementVM; }

    public MeetingNotesVM getMeetingNotesVM() { return  meetingNotesVM; }
    public SprintBacklogVM getSprintBacklogVM() { return  sprintBacklogVM; }




    public void instantiateViewModels(ViewHandler viewHandler) {
        loginVM = new LoginVM(dataModel, viewHandler);
        registerVM = new RegisterVM(dataModel, viewHandler);
        homepageVM = new HomepageVM(dataModel, viewHandler);
        productBacklogVM = new ProductBacklogVM(dataModel, viewHandler);
        addMemberVM = new AddMemberVM(dataModel, viewHandler);
        createGroupVM = new CreateGroupVM(dataModel, viewHandler);
        addMemberVM = new AddMemberVM(dataModel, viewHandler);
        groupManagementVM = new GroupManagementVM(dataModel, viewHandler);
        sprintManagementVM = new SprintManagementVM(dataModel, viewHandler);
        meetingNotesVM = new MeetingNotesVM(dataModel, viewHandler);
        sprintBacklogVM = new SprintBacklogVM(dataModel,viewHandler);


    }
}



