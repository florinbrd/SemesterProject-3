package contract;

import contract.client.*;

import contract.validation.Validator;
import network.IClient;
import network.IServer;
import javaModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.rmi.RemoteException;
import java.util.ArrayList;


@SpringBootApplication
public class App implements CommandLineRunner, IServer {

    @Autowired
    UserClient userClient;
    @Autowired
    BacklogClient backlogClient;
    @Autowired
    GroupClient groupClient;
    @Autowired
    SprintClient sprintClient;
    @Autowired
    NotesClient notesClient;


    Validator validator = new Validator();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    public App() {
    }


    @Override
    public boolean addAccount(User u, String clientId) throws RemoteException {
        return validator.validateCreateUser(userClient.CreateUser(u), clientId);

    }


    @Override
    public Session Login(Login l, String clientId) throws RemoteException {
        return validator.validateLogin(userClient.PostLogin(l), clientId);


    }

    @Override
    public boolean createGroup(Group group, String clientId) throws RemoteException {
        return validator.validateCreateGroup(groupClient.postGroup(group), clientId);
    }

    @Override
    public boolean addGroupMember(GroupMember groupMember, String clientId) throws RemoteException {
        return validator.validateAddGroupMember(groupClient.postMember(groupMember), clientId);

    }

    @Override
    public boolean removeGroupMember(GroupMember groupMember, String clientId) throws RemoteException {
        return validator.validateDeleteGroupMember(groupClient.deleteMember(groupMember), clientId);
    }

    @Override
    public boolean addStoryToProductBacklog(UserStory userStory, String clientId) throws RemoteException {
        return validator.validateAddUserStory(backlogClient.addUserStory(userStory), clientId);

    }

    @Override
    public boolean setNumberOfSprints(SprintList sprintList, String clientId) throws RemoteException {
        return validator.validateEditSprintInfo(sprintClient.setSprintDuration(sprintList), clientId);

    }

    @Override
    public boolean setDurationOfSprints(SprintList sprintList, String clientId) throws RemoteException {
        return validator.validateEditSprintInfo(sprintClient.setSprintNumber(sprintList), clientId);
    }

    @Override
    public boolean addStoryToSprint(UserStory story, String clientId) throws RemoteException {

        return validator.validateAddUserStory(
                sprintClient.postStoryToSprint(story), clientId);

    }

    @Override
    public boolean removeUserStoryFromSprint(UserStory userStory, String clientId) throws RemoteException {
        return validator.validateDeleteUserStory(sprintClient.postFromSprintToBacklog(userStory), clientId);
    }

    @Override
    public boolean addTaskToUserStory(Task task, String clientId) throws RemoteException {
        return validator.validateAddTasktoUserStory(sprintClient.addTaskToUserStory(task), clientId);

    }

    @Override
    public String addClient(IClient client) throws RemoteException {
//        clientHandler.addClient("clientId",client);
        return "clientId";
    }

    @Override
    public Group loadGroup(int groupId, String cliendId) throws RemoteException {
        return groupClient.getGroup(groupId);
    }

    @Override
    public boolean addMinutesOfMeeting(MeetingNotes notes, String clientId) throws RemoteException {
        return validator.validateAddNotes(notesClient.postNotes(notes), clientId);
    }

    @Override
    public boolean changeGroupMemberRole(GroupMember groupMember, String clientId) throws RemoteException {
        return validator.validateChangeGroupMemberRole(groupClient.changeGroupRole(groupMember), clientId);
    }

    @Override
    public ProductBacklog loadProductBacklog(int groupId) throws RemoteException {
        return backlogClient.getBacklog(groupId);
    }

    @Override
    public Sprint loadSprintBacklog(int sprintNumber, int groupId) throws RemoteException {
        return sprintClient.getSprintBacklog(sprintNumber, groupId);
    }

    @Override
    public ArrayList<String> loadAllUsernames() {
        return userClient.loadAllUsernames();
    }

    @Override
    public Session getSessionInformation(String username) {
        return userClient.getSession(username);
    }

    @Override
    public MeetingNotes loadMeetingNotes(int meetingId) {
        return notesClient.loadMeetingNotes(meetingId);
    }

    @Override
    public UserStory loadTasks(int storyId) throws RemoteException {
       return sprintClient.getTasks(storyId);
    }


    @Override
    public void run(String... strings) throws Exception {
    }
}
