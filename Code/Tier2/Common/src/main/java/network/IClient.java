package network;

import javaModel.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IClient extends Remote {
    boolean addAccount(User u) throws RemoteException;

    Session Login(Login l) throws RemoteException;

    boolean createGroup(Group group) throws RemoteException;

    boolean addGroupMember(GroupMember groupMember) throws RemoteException;

    boolean removeGroupMember(GroupMember groupMember) throws RemoteException;



    boolean addStoryToProductBacklog(UserStory userStory) throws RemoteException;

    boolean setNumberOfSprints(SprintList sprintList) throws RemoteException;

    boolean setDurationOfSprints(SprintList sprintList) throws RemoteException;

    boolean addStoryToSprint(UserStory story) throws RemoteException;

    boolean removeUserStoryFromSprint(UserStory userStory) throws RemoteException;

    boolean addTaskToUserStory(Task task) throws RemoteException;


    boolean createMinutesOfMeeting(MeetingNotes notes) throws RemoteException;

    void setClientId () throws  RemoteException;

    boolean changeGroupMemberRole (GroupMember groupMember) throws RemoteException;


    int getNumberOfSprints(int groupId) throws  RemoteException;

    boolean setActiveSprint(SprintList sprintList) throws RemoteException;

    ProductBacklog loadProductBacklog (int groupId) throws RemoteException;

    Sprint loadSprintBacklog (int sprintNumber,int groupId) throws  RemoteException;

    Group loadGroup(int groupID) throws RemoteException;

    ArrayList<String> loadAllUsernames() throws RemoteException;

    Session getSessionInformation(String username) throws RemoteException;

    MeetingNotes loadMeetingNotes(int meetingId) throws RemoteException;

    UserStory loadTasks(int storyId) throws RemoteException;
}

