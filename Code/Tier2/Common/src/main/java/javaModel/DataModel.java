package javaModel;


import network.IClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class DataModel implements IDataModel {

    public User user;
    public Group group;
    public Sprint sprint;
    public ArrayList<SprintList> sprintList;
    public ArrayList<User> users;
    public ArrayList<GroupMember> groupMembers;
    public PropertyChangeSupport propertyChangeSupport;
    public Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    private IClient client;

    public DataModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        users = new ArrayList<>();
        group = new Group();
        sprint = new Sprint();
        sprintList = new ArrayList<>();
        groupMembers = new ArrayList<>();
        session = new Session();
        user = new User();

    }

    @Override
    public Session login(Login login) throws RemoteException {
        Session session = client.Login(login);
        this.session = session;
        System.out.println(session.getGroupId() + " " + session.getUsername());
        return session;
    }

    @Override
    public boolean createAccount(User user) throws RemoteException {
        return client.addAccount(user);
    }

    @Override
    public boolean addUserStory(UserStory e) throws RemoteException {
        e.setGroupId(session.getGroupId());


        return client.addStoryToProductBacklog(e);
    }

    @Override
    public boolean removeUserStory(UserStory e) {
        return false;
    }

    @Override
    public void addListener(String evt, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(evt, listener);
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        System.out.println("Client: Product Backlog received from server");
        propertyChangeSupport.firePropertyChange("GetProductBacklogFromServer", null, productBacklog);
        // todo maybe we can use this for automatic updates from other client

    }

    public void loadUsers(ArrayList<User> users) {
        this.users = users;
        System.out.println("Client: Users list received from server");
        propertyChangeSupport.firePropertyChange("GetAvailableUsersFromServer", null, users);
    }

    @Override
    public boolean addGroupMember(GroupMember groupMember) throws RemoteException {
        boolean confirmation;
        confirmation = client.addGroupMember(groupMember);
        if (confirmation) {
            group.addMember(groupMember);
            propertyChangeSupport.firePropertyChange("NewGroupMember", null, groupMember);
        }

        return confirmation;

    }

    @Override
    public boolean removeGroupMember(GroupMember member) throws RemoteException {
        boolean confirmation;
        confirmation = client.removeGroupMember(member);

        return confirmation;
    }


    @Override
    public boolean createGroup(Group group) throws RemoteException {
        boolean confirmation;
        confirmation = client.createGroup(group);
        if (confirmation) {
            group.setGroupMembers(groupMembers);
            getSession();
            propertyChangeSupport.firePropertyChange("GroupCreated", null, group);
        }
        return confirmation;

    }

    @Override
    public boolean setNumberOfSprints(SprintList sprintList) throws RemoteException {
        boolean confirmation = client.setNumberOfSprints(sprintList);
        if (confirmation) {
            session.setNumberOfSprints(sprintList.getNumberOfSprints());
            return true;
        }
        return false;
    }


    @Override
    public boolean setDurationOfSprints(SprintList sprintList) throws RemoteException {
        return client.setDurationOfSprints(sprintList);
    }

    @Override
    public boolean setActiveSprint(SprintList sprintList) throws RemoteException {
        return client.setActiveSprint(sprintList);
    }

    @Override
    public ArrayList<String> loadAllUsernames() throws RemoteException {
        return client.loadAllUsernames();
    }

    @Override
    public Sprint loadSprint(int sprintNumber) throws RemoteException {
        return client.loadSprintBacklog(sprintNumber, session.getGroupId());
    }

    @Override
    public MeetingNotes loadMeetingNotes() throws RemoteException {

        return client.loadMeetingNotes(session.getGroupId());
    }

    @Override
    public UserStory loadTasks(int storyId) throws RemoteException {
       return client.loadTasks(storyId);
    }


    @Override
    public boolean AddUserStoryToSprint(UserStory userstory) throws RemoteException {
        userstory.setGroupId(session.getGroupId());
        userstory.setSprintId(3);
        return client.addStoryToSprint(userstory);
    }

    @Override
    public boolean removeUserStoryFromSprint(UserStory userstory) throws RemoteException {
        userstory.setGroupId(session.getGroupId());
        return client.removeUserStoryFromSprint(userstory);
    }


    @Override
    public boolean AddTasksToUserStories(Task task) throws RemoteException {
        return client.addTaskToUserStory(task);
    }

    @Override
    public boolean setClient(IClient client) {
        this.client = client;
        return true;
    }

    @Override
    public IClient getclient() {
        return client;
    }

    @Override
    public boolean changeRole(GroupMember groupMember) throws RemoteException {
        return client.changeGroupMemberRole(groupMember);
    }

    @Override
    public boolean createMinutesofTheMeeting(MeetingNotes meetingNotes) throws RemoteException {

        meetingNotes.setGroupId(session.getGroupId());
        return client.createMinutesOfMeeting(meetingNotes);
    }


    @Override
    public void getGroupInformation(String username) throws RemoteException {

        System.out.println("??????????????????????????????????????????????????????");
    }

    @Override
    public int getNumberOfSprints() {
        return session.getNumberOfSprints();
    }

    @Override
    public ProductBacklog loadProductBacklog() throws RemoteException {

        return client.loadProductBacklog(session.getGroupId());

    }


    @Override
    public Group loadGroup() throws RemoteException {
        return client.loadGroup(session.getGroupId());

    }

    public void getSessionInformation() throws RemoteException {
        client.getSessionInformation(session.getUsername());
    }

}



