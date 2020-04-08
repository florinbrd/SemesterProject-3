package network;


import com.google.gson.Gson;
import javaModel.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import java.io.Serializable;
import java.net.MalformedURLException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

@SpringBootApplication
public class Client implements Runnable, IClient, Serializable {


    @Bean
    RmiProxyFactoryBean service() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:1099/IServer");
        rmiProxyFactory.setServiceInterface(IServer.class);
        return rmiProxyFactory;
    }


    public IServer server;

    private String clientId;

    public IServer getServer() {
        return server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Client() throws RemoteException, NotBoundException, MalformedURLException {

    }

    public void test() throws RemoteException {
        User jamie = new User("Jamie", "Lopez", "email@", "account", "pswd", 333);

    }


    @Override
    public boolean addAccount(User u) throws RemoteException {
        return server.addAccount(u, this.clientId);
    }

    @Override
    public Session Login(Login l) throws RemoteException {
        return server.Login(l, this.clientId);
    }

    @Override
    public boolean createGroup(Group group) throws RemoteException {
        return server.createGroup(group, this.clientId);

    }

    @Override
    public boolean addGroupMember(GroupMember groupMember) throws RemoteException {
        return server.addGroupMember(groupMember, this.clientId);
    }

    @Override
    public boolean removeGroupMember(GroupMember groupMember) throws RemoteException {
        return server.removeGroupMember(groupMember, this.clientId);
    }

    @Override
    public boolean addStoryToProductBacklog(UserStory userStory) throws RemoteException {
        return server.addStoryToProductBacklog(userStory, this.clientId);
    }

    @Override
    public boolean setNumberOfSprints(SprintList sprintList) throws RemoteException {
        return server.setNumberOfSprints(sprintList, this.clientId);
    }

    @Override
    public boolean setDurationOfSprints(SprintList sprintList) throws RemoteException {
        return server.setDurationOfSprints(sprintList, this.clientId);
    }

    @Override
    public boolean addStoryToSprint(UserStory story) throws RemoteException {
        return server.addStoryToSprint(story, this.clientId);
    }

    @Override
    public boolean removeUserStoryFromSprint(UserStory userStory) throws RemoteException {
        return server.removeUserStoryFromSprint(userStory, this.clientId);
    }

    @Override
    public boolean addTaskToUserStory(Task task) throws RemoteException {
        return server.addTaskToUserStory(task, this.clientId);
    }


    @Override
    public boolean createMinutesOfMeeting(MeetingNotes notes) throws RemoteException {
        return server.addMinutesOfMeeting(notes, this.clientId);
    }

    @Override
    public void setClientId() throws RemoteException {
        setClientId(server.addClient(this));

    }

    @Override
    public boolean changeGroupMemberRole(GroupMember groupMember) throws RemoteException {
        return server.changeGroupMemberRole(groupMember, this.clientId);
    }


    @Override
    public int getNumberOfSprints(int groupId) {
        System.out.println("THIS METHOD IS NOT WORKING FOR NOW");
       return 0;
    }

    @Override
    public boolean setActiveSprint(SprintList sprintList) {
        return false;
    }

    @Override
    public ProductBacklog loadProductBacklog(int groupId) throws RemoteException {
        return server.loadProductBacklog(groupId);
    }

    @Override
    public Sprint loadSprintBacklog(int sprintNumber, int groupId) throws RemoteException {
        return server.loadSprintBacklog(sprintNumber, groupId);
    }

    @Override
    public Group loadGroup(int groupID) throws RemoteException {
        return server.loadGroup(groupID, this.clientId);
    }

    @Override
    public ArrayList<String> loadAllUsernames() throws RemoteException {
        return server.loadAllUsernames();
    }

    @Override
    public Session getSessionInformation(String username) throws RemoteException {
        return server.getSessionInformation(username);
    }

    @Override
    public MeetingNotes loadMeetingNotes(int meetingId) throws RemoteException {
        return server.loadMeetingNotes(meetingId);
    }

    @Override
    public UserStory loadTasks(int storyId) throws RemoteException {
        return server.loadTasks(storyId);
    }

    public void injectServer(IServer server) {
        this.server = server;
    }


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
//        SpringApplication.run(Client.class, args);

        Client client = new Client();
        client.run();


        User user = new User("maxacas", "Test 2", "Test 3", "maxacas", "ds", 1);
        User user1 = new User("admin", "Test 2", "Test 3", "admin", "ds", 2);
        GroupMember groupMember = new GroupMember(user, "ProductOwner");
        GroupMember groupMember2 = new GroupMember(user1, "ScrumMaster");
        groupMember.setGroupID(1);
        Sprint sprint = new Sprint();
        ProductBacklog productBacklog = new ProductBacklog();
        SprintList sprintList = new SprintList();
        sprintList.setNumberOfSprints(3);
        sprintList.setDuration(4);
        Group group = new Group(sprintList, productBacklog, 3);
        Task task = new Task();
        task.setTaskMessage("working on the mvvmpattern");
        group.addMember(groupMember);
        Login login = new Login("admin", "admin");
        UserStory userStory = new UserStory("test user story", 100, 0);
        userStory.setGroupId(-1);
        MeetingNotes notes =
                new MeetingNotes("PlanningMeeting", "Retrospective", "Review", "Daily");
        group.addMember(groupMember);
        group.addMember(groupMember2);


        //  client.server.createGroup(group,"dsds"); todo error object not sent correctly?

        Gson gson = new Gson();
        String json = "{\n" +
                "    \"storyId\": 3,\n" +
                "    \"storyPoint\": 3,\n" +
                "    \"userStory\": \"heyman\",\n" +
                "    \"currentlyActive\": true,\n" +
                "    \"completed\": 0,\n" +
                "    \"taskList\": [\n" +
                "        {\n" +
                "            \"validated\": false,\n" +
                "            \"storyId\": 3,\n" +
                "            \"taskMessage\": \"HeyTask\",\n" +
                "            \"storyPoints\": 3,\n" +
                "            \"completed\": false,\n" +
                "            \"taskId\": 3\n" +
                "        }\n" +
                "    ],\n" +
                "    \"sprintId\": 2,\n" +
                "    \"groupId\": 3,\n" +
                "    \"validated\": true\n" +
                "}";
        client.server.loadProductBacklog(3);

    }

    @Override
    public void run() {
        IServer server = SpringApplication
                .run(Client.class).getBean(IServer.class);
        try {
            this.injectServer(server);
            System.out.println("RUN METHOD");
        } catch (Exception e) {
            e.printStackTrace();

        }
        User user = new User("maxacas", "Test 2", "Test 3", "maxacas", "ds", 1);

    }
}
