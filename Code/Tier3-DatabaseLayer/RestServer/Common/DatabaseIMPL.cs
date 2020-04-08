using System.Collections.Generic;
using WebApplication.Database.Data;
using WebApplication.Database.Repository;

namespace WebApplication.Common
{
    public class DatabaseIMPL : IDatabaseServer
    {
        
        static ScrumContext _scrumContext = new ScrumContext();
        GroupRepository _groupRepository = new GroupRepository(_scrumContext);
        
        public string GetPassword(string username)
        {
           return _groupRepository.GetPassword(username);
        }

        public bool CreateAccount(User user)
        {
            return _groupRepository.CreateAccount(user.username,user.password);
            
        }

        public bool CreateGroup(Group group)
        {
            return _groupRepository.CreateGroup(group);
        }

        public bool AddMember(GroupMember groupMember)
        {
          return  _groupRepository.AddMember(groupMember);
        }

        public bool RemoveMember(GroupMember groupMember)
        {
           return _groupRepository.RemoveMember(groupMember);
        }

        public bool AddStory(UserStory userStory)
        {
           return _groupRepository.AddStory(userStory);
        }

        public List<string> GetAllUsernames()
        {
            return _groupRepository.GetAllUsernames();
        }

        public bool setSprintDuration(SprintList sprintList)
        {
            return _groupRepository.SetDurationOfSprints(sprintList);
        }

        public bool setSprintNumber(SprintList sprintList)
        {
            return _groupRepository.SetNumberOfSprints(sprintList);
        }

        public bool addUserStoryToSprint(UserStory userStory)
        {
            return _groupRepository.AddUserStoryToSprint(userStory);
        }

        public bool removeUserStoryFromSprint(UserStory userStory)
        {
            return _groupRepository.RemoveUserStoryFromSprint(userStory);
        }

        public bool addTaskToUserStory(Tasks task)
        {
            return _groupRepository.AddTaskToUserStory(task);
        }

        public bool addMeetingNotes(MeetingNotes meetingNotes)
        {
            return _groupRepository.CreateMinutesOfMeeting(meetingNotes);
        }

        public bool changeGroupRole(GroupMember groupMember)
        {
            return _groupRepository.ChangeRole(groupMember);
        }

        public ProductBacklog loadProductBacklog(int groupId)
        {
            return _groupRepository.LoadProductBacklog(groupId);
        }

        public Sprint loadSprintBacklog(int sprintNumber, int groupId)
        {
            return _groupRepository.LoadSprintBacklog(sprintNumber, groupId);
        }

        public Group loadGroup(int groupId)
        {
            return _groupRepository.LoadGroup(groupId);
        }

        public Session getSession(string username)
        {
            return _groupRepository.GetGroupInformation(username);
        }

        public MeetingNotes loadMeetingNotes(int meetingId)
        {
            return _groupRepository.LoadMeetingNotes(meetingId);
        }

        public UserStory loadTasks(int storyId)
        {
            return _groupRepository.LoadUserStory(storyId);
        }
    }
}