using System;
using System.Collections.Generic;
using WebApplication.Common;


namespace WebApplication.validation
{
    public class Validator
    {
        private IDatabaseServer _server;

        public Boolean ValidatePassword(Login login)
        {
//            return login.password.Equals(_server.GetPassword(login.username));
            return true;
        }

        public Boolean CreateAccount(User user)
        {
//            return _server.CreateAccount(user.GetAccount(), user.GetAccount());
            return true;
        }

        public Boolean CreateGroup(Group group)
        {
//            return _server.CreateGroup(group.GetGroupMembers());
            return true;
        }

        public Boolean AddMember(GroupMember groupMember, int groupID)
        {
//            return _server.AddMember(groupMember,groupID);
            return true;
        }

        public Boolean RemoveMember(GroupMember groupMember)
        {
//            return _server.RemoveMember("s",9999);
            return true;
        }

        public List<string> getAllUsers()
        {
//            return _server.GetAllUsernames();
            return new List<string>();
        }

        public Boolean AddStory(UserStory userStory, int groupID)
        {
//    return _server.AddStory(userStory,groupID);       
            return true;
        }


        public ProductBacklog GetProductBacklog(int groupId)
        {
//            return _server.getProductBacklog(groupId);
            return null;
        }

        public Boolean SetSprintDuration(SprintList sprintList)
        {
            return true;
        }

        public Boolean SetSprintNumber(SprintList sprintList)
        {
            return true;
        }
        public Boolean AddUserStoryToSprint(UserStory userStory, Sprint sprint)
        {
            return true;
        }
        public Boolean RemoveUserStoryFromSprint(UserStory userStory)
        {
            return true;
        }
        public Boolean AddTaskToUserStory(UserStory userStory, Tasks task)
        {
            return true;
        }
    }
}