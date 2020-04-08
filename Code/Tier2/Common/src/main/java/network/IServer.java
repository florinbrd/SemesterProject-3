package network;

import javaModel.*;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServer extends Remote  {


    boolean addAccount(User u, String clientId) throws RemoteException;

    Session Login(Login l, String clientId) throws RemoteException;

    boolean createGroup(Group group, String clientId) throws RemoteException;

    boolean addGroupMember(GroupMember groupMember, String clientId) throws RemoteException;

    boolean removeGroupMember(GroupMember groupMember, String clientId) throws RemoteException;

    boolean addStoryToProductBacklog(UserStory userStory, String clientId) throws RemoteException;

    boolean setNumberOfSprints(SprintList sprintList, String clientId) throws RemoteException;

    boolean setDurationOfSprints(SprintList sprintList, String clientId) throws RemoteException;

    boolean addStoryToSprint(UserStory story , String clientId) throws RemoteException;

    boolean removeUserStoryFromSprint(UserStory userStory, String clientId) throws RemoteException;


    boolean addTaskToUserStory(Task task, String clientId) throws RemoteException;

    String addClient(IClient client) throws RemoteException;

    Group loadGroup(int groupId, String cliendId) throws RemoteException;

    boolean addMinutesOfMeeting(MeetingNotes notes, String clientId) throws RemoteException;
    boolean changeGroupMemberRole(GroupMember groupMember,String clientId) throws  RemoteException;

    ProductBacklog loadProductBacklog(int groupId) throws RemoteException;
    Sprint loadSprintBacklog (int sprintNumber,int groupId) throws  RemoteException;
    ArrayList<String> loadAllUsernames() throws  RemoteException;

    Session getSessionInformation(String username) throws  RemoteException;

    MeetingNotes loadMeetingNotes(int meetingId) throws RemoteException;

    UserStory loadTasks(int storyId) throws  RemoteException;
}


