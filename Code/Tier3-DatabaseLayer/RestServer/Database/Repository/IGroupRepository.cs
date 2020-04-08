using System;
using System.Collections.Generic;
using WebApplication.Common;

namespace WebApplication.Database.Repository
{
    public interface IGroupRepository : IDisposable
    {
        string GetPassword(string username);
        bool CreateAccount(string username, string password);
        bool CreateGroup(Group group); 
        bool AddMember(GroupMember groupMember);
        bool RemoveMember(GroupMember groupMember);
        bool AddStory(UserStory userStory);
        bool SetNumberOfSprints(SprintList sprintList);
        bool AddTaskToUserStory(Tasks task);
        bool RemoveUserStoryFromSprint(UserStory userStory);
        bool AddUserStoryToSprint(UserStory userStory);
        bool SetDurationOfSprints(SprintList sprintList);
        bool SetActiveSprint(SprintList sprintList);
        bool ChangeRole(GroupMember groupMember);
        bool CreateMinutesOfMeeting(MeetingNotes note);
        List<string> GetAllUsernames();
        ProductBacklog LoadProductBacklog(int groupId);
        Session GetGroupInformation(string username);
        Sprint LoadSprintBacklog(int sprintNumber, int groupId);
        Group LoadGroup(int groupId);
        MeetingNotes LoadMeetingNotes(int groupId);
        UserStory LoadUserStory(int storyId);

    }
}