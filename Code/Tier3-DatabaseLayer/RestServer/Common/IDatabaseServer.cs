﻿﻿using System.Collections.Generic;

  namespace WebApplication.Common
{
   
    public interface IDatabaseServer
    {
       
        string GetPassword(string username);
        bool CreateAccount(User user);
        bool CreateGroup(Group @group);
        bool AddMember(GroupMember groupMember);
        bool RemoveMember(GroupMember groupMember);
        bool AddStory(UserStory userStory);
        List<string> GetAllUsernames();
        bool setSprintDuration(SprintList sprintList);
        bool setSprintNumber(SprintList sprintList);
        bool addUserStoryToSprint(UserStory userStory);

        bool removeUserStoryFromSprint(UserStory userStory);

        bool addTaskToUserStory(Tasks task);

        bool addMeetingNotes(MeetingNotes meetingNotes);
        
        bool changeGroupRole(GroupMember groupMember);
        ProductBacklog loadProductBacklog(int groupId);

        Sprint loadSprintBacklog(int sprintNumber,int groupId);
        Group loadGroup(int groupId);

        Session getSession(string username);

        MeetingNotes loadMeetingNotes(int meetingId);

        UserStory loadTasks(int storyId);
    }
    
}
