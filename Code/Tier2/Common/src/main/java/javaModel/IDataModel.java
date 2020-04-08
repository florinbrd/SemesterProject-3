package javaModel;

import network.IClient;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IDataModel {
    boolean setClient(IClient client);

    Session getSession();

    IClient getclient();

    Session login(Login login) throws RemoteException;

    boolean createGroup(Group group) throws RemoteException;

    boolean setNumberOfSprints(SprintList sprintList) throws RemoteException;

    boolean setDurationOfSprints(SprintList sprintList) throws RemoteException;

    boolean AddTasksToUserStories(Task task) throws RemoteException;

    boolean createMinutesofTheMeeting(MeetingNotes meetingNotes) throws RemoteException;

    boolean changeRole(GroupMember groupMember) throws RemoteException;


    boolean addUserStory(UserStory e) throws RemoteException;

    boolean addGroupMember(GroupMember member) throws RemoteException;

    void addListener(String evt, PropertyChangeListener listener);

    boolean AddUserStoryToSprint(UserStory userstory) throws RemoteException;

    boolean createAccount(User user) throws RemoteException;

    boolean removeUserStory(UserStory e);

    boolean removeGroupMember(GroupMember member) throws RemoteException;

    boolean removeUserStoryFromSprint(UserStory userstory) throws RemoteException;

    void getGroupInformation(String username) throws RemoteException;

    int getNumberOfSprints() throws RemoteException;

    ProductBacklog loadProductBacklog() throws RemoteException;

    Group loadGroup() throws RemoteException;

    boolean setActiveSprint(SprintList sprintList) throws RemoteException;

    void setSession(Session session);

    ArrayList<String> loadAllUsernames() throws RemoteException;

    Sprint loadSprint(int sprintNumber) throws RemoteException;

    MeetingNotes loadMeetingNotes () throws RemoteException;

    UserStory loadTasks(int storyId) throws  RemoteException;
}
