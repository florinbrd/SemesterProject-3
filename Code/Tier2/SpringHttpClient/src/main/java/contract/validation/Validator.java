package contract.validation;

import contract.App;
import contract.network.RMIClientHandler;
import javaModel.*;

import java.rmi.RemoteException;

public class Validator {
    private RMIClientHandler rmiClientHandler;

    public Validator(RMIClientHandler clientHandler) throws RemoteException {
        this.rmiClientHandler = clientHandler;
    }

    public Validator() {

    }

    public boolean validateCreateUser(User user, String clientId) {

        if (user != null) {
            if (user.isValidated()) {
//                rmiClientHandler.getClient(clientId).addAccount(user);
                System.out.println("------------------------Useer passed to client=" + user.isValidated() + "--------------------");
                return true;

            } else {
                System.out.println("------------------------Null passed to client user validted=" + user.isValidated() + "--------------------");
                return false;
            }

        }
        System.out.println("USER IS NULL");
        return false;

    }

    public Session validateLogin(Session session, String clientId) {
        if (session != null) {
            if (session.isValidated()) {
                System.out.println("------------------------Login passed to client=" + session.isValidated() + "--------------------");
                return session;

            } else {
                System.out.println("------------------------Null passed to client user validted=" + session.isValidated() + "--------------------");
                return session;
            }

        }
        System.out.println("LOGIN IS NULL");
        return session;
    }

    public boolean validateCreateGroup(Group group, String clientId) {
        if (group != null) {
            if (group.isValidated()) {
                System.out.println("------------------------Group passed to client=" + group.isValidated() + "--------------------");
                return true;
            } else {
                System.out.println("------------------------Null passed to client group validated=" + group.isValidated() + "--------------------");
                return false;
            }
        }
        return false;

    }

    public boolean validateAddGroupMember(GroupMember groupMember, String clientId) {
        if (groupMember != null) {
            if (groupMember.isValidated()) {
                System.out.println("------------------------GroupMember passed to client=" + groupMember.isValidated() + "--------------");
                return true;
            } else {
                System.out.println("------------------------Null passed to client GroupMember validated=" + groupMember.isValidated() + "---------------");
                return false;
            }
        }
        return false;
    }

    public boolean validateDeleteGroupMember(boolean bol, String clientId) {

        if (bol) {
            System.out.println("------------------------TRUE passed to client=" + "--------------");
            return true;
        } else {
            System.out.println("------------------------FALSE passed to client ---------------");
            return false;
        }
    }

    public boolean validateAddUserStory(UserStory userStory, String clientID) {
        if (userStory.isValidated()) {
            System.out.println("------------------------UserStory=true passed to client=" + "--------------");
            return true;
        } else {
            System.out.println("------------------------UserStory=false passed to client ---------------");
            return false;
        }

    }

    public boolean validateAddNotes(MeetingNotes meetingNotes, String clientID) {
        if (meetingNotes.isValidated()) {
            System.out.println("------------------------MeetingNotes=true passed to client=" + "--------------");
            return true;
        } else {
            System.out.println("------------------------MeetingNotes=false passed to client ---------------");
            return false;
        }


    }

    public boolean validateEditSprintInfo(SprintList sprintList, String clientID) {
        if (sprintList.isValidated()) {
            System.out.println("------------------------SprintList=true passed to client=" + "-------------- duration:"+sprintList.duration+"number:"+sprintList.numberOfSprints);
            return true;
        } else {
            System.out.println("------------------------SprintList=false passed to client --------------duration:"+sprintList.duration+"number:"+sprintList.numberOfSprints);
            return false;
        }

    }

    public boolean validateAddTasktoUserStory(Task task, String clientId) {
        if (task.isValidated()) {
            System.out.println("------------------------Task=true passed to client=" + "--------------");
            return true;
        } else {
            System.out.println("------------------------Task=false passed to client ---------------");
            return false;
        }

    }

    public boolean validateDeleteUserStory(UserStory userStory, String clientId) {

        if (userStory.isValidated()) {
            System.out.println("------------------------TRUE passed to client=" + "--------------");
            return true;
        } else {
            System.out.println("------------------------FALSE passed to client ---------------");
            return false;
        }
    }

    public boolean validateChangeGroupMemberRole(GroupMember groupMember, String clientId) {
        if (groupMember.isValidated()) {
            System.out.println("------------------------GroupMemberROLE=true passed to client=" + "--------------");
            return true;
        } else {
            System.out.println("------------------------GroupMemberROLE=false passed to client ---------------");
            return false;
        }

    }



}



