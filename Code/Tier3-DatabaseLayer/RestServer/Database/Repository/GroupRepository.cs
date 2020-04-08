using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using WebApplication.Common;
using WebApplication.Database.Data;
using WebApplication.Database.Models;
using Group = WebApplication.Common.Group;
using ProductBacklog = WebApplication.Database.Models.ProductBacklog;
using SprintList = WebApplication.Common.SprintList;
using User = WebApplication.Database.Models.User;
using UserStory = WebApplication.Common.UserStory;
using GroupMember = WebApplication.Common.GroupMember;
using MeetingNotes = WebApplication.Common.MeetingNotes;
using Sprint = WebApplication.Common.Sprint;


namespace WebApplication.Database.Repository
{
    public class GroupRepository : IGroupRepository
    {
        private ScrumContext db;

        public GroupRepository(ScrumContext db)
        {
            this.db = db;
        }

        public void Dispose()
        {
            throw new NotImplementedException();
        }

        public string GetPassword(string username)
        {
            return db.Users.Find(username).Password;
        }

        public bool CreateAccount(string username, string password)
        {
            Console.WriteLine(username + " " + password);
            try
            {
                db.Add(new User
                {
                    Username = username,
                    Password = password,
                });

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool CreateGroup(Group group)
        {
            try
            {
                var groupId = db.Groups.Count() + 1;
                List<GroupMember> groupMembers = new List<GroupMember>();
                foreach (GroupMember groupMember in group.groupMembers)
                {
                    groupMembers.Add(groupMember);
                }

                db.Add(new Database.Models.Group
                {
                    GroupId = groupId
                });
                db.SaveChanges();

                //Creating group creates automatically PB 
                db.Add(new ProductBacklog
                {
                    GroupId = groupId
                });
                db.SaveChanges();

                //Creating SprintList with no data 
                db.Add(new Models.SprintList
                {
                    GroupId = groupId,
                    Duration = 0,
                    NumberOfSprints = 0
                });
                db.SaveChanges();

                //Create all users that are in the group
                foreach (GroupMember groupMember in groupMembers)
                {
                    db.Add(new Models.GroupMember
                    {
                        Username = groupMember.user.username,
                        GroupId = groupId,
                        GroupRole = groupMember.role
                    });
                }

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool AddMember(GroupMember groupMember)
        {
            try
            {
                db.Add(new Models.GroupMember
                {
                    Username = groupMember.user.username,
                    GroupId = groupMember.groupID,
                    GroupRole = groupMember.role,
                });
                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool RemoveMember(GroupMember groupMember)
        {
            try
            {
                var memberItem = db.GroupMembers.Find(groupMember.user.username);
                var groupId = db.GroupMembers.Find(groupMember.user.username).GroupId;
                var counter = db.GroupMembers.Count(x => x.GroupId == groupId);
                if (counter > 1)
                {
                    db.GroupMembers.Remove(memberItem);
                    db.SaveChanges();
                    return true;
                }

                return false;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool AddStory(UserStory userStory)
        {
            try
            {
                var storyId = db.UserStories.Count() + 1;
                var groupId = userStory.groupId;
                var message = userStory.userStory;
                var storyPoints = userStory.storyPoint;
                var currentlyActive = userStory.currentlyActive;
                var completed = false;
                completed = userStory.completed == 1;

                db.Add(new Models.UserStory
                {
                    StoryId = storyId,
                    GroupId = groupId,
                    Story = message,
                    SprintId = -1,
                    StoryPoint = storyPoints,
                    CurrentlyActive = currentlyActive,
                    Completed = completed
                });
                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public List<string> GetAllUsernames()
        {
            var usernames = new List<string>();
            var users = from user in db.Users
                join groupMember in db.GroupMembers on user.Username equals groupMember.Username into temp
                from groupMember in temp.DefaultIfEmpty()
                where groupMember.Username.Equals(null)
                select user.Username;


            foreach (var user in users)
            {
                usernames.Add(user);
            }

            // foreach (var user in db.Users)
            // {
            //     usernames.Add(user.Username);
            // }
            return usernames;
        }

        public bool SetNumberOfSprints(SprintList sprintList)
        {
            try
            {
                var numberOfSprints = sprintList.numberOfSprints;
                var groupId = sprintList.groupID;

                var sprintListItem = db.SprintLists.Find(groupId);
                var oldValue = sprintListItem.NumberOfSprints;
                var difference = numberOfSprints - oldValue;
                sprintListItem.NumberOfSprints = numberOfSprints;

                var edit = Enumerable.Any(db.Sprints, sprint => sprint.GroupId == groupId);
                if (edit == false)
                {
                    for (var i = 1; i <= numberOfSprints; i++)
                    {
                        db.Add(new Models.Sprint
                        {
                            SprintId = db.Sprints.Count() + i,
                            GroupId = groupId,
                            SprintNumber = i
                        });
                    }
                }
                else
                {
                    if (difference > 0)
                    {
                        for (var i = oldValue + 1; i <= numberOfSprints; i++)
                        {
                            db.Add(new Models.Sprint
                            {
                                SprintId = db.Sprints.Count() + i,
                                GroupId = groupId,
                                SprintNumber = i
                            });
                        }
                    }
                    else if (difference < 0)
                    {
                        for (var i = oldValue; i > numberOfSprints; i--)
                        {
                            var sprintItem =
                                db.Sprints.FirstOrDefault(x => x.GroupId == groupId && x.SprintNumber == i);
                            db.Sprints.Remove(sprintItem);
                        }
                    }
                    else
                    {
                    }
                }

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool SetDurationOfSprints(SprintList sprintList)
        {
            try
            {
                var duration = sprintList.duration;
                var groupId = sprintList.groupID;

                var sprintListItem = db.SprintLists.Find(groupId);
                sprintListItem.Duration = duration;

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool SetActiveSprint(SprintList sprintList)
        {
            try
            {
                var activeSprint = sprintList.currentlyActiveSprint;
                var groupId = sprintList.groupID;

                var sprintListItem = db.SprintLists.Find(groupId);
                sprintListItem.CurrentlyActiveSprint = activeSprint;

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool AddUserStoryToSprint(UserStory userStory)
        {
            try
            {
                var groupId = userStory.groupId;
                var currentlyActiveSprint = db.SprintLists.Find(groupId).CurrentlyActiveSprint;
                var exist = db.Sprints
                    .Any(x => x.GroupId == groupId && x.SprintNumber == currentlyActiveSprint);
                if (exist)
                {
                    var sprintId = db.Sprints
                        .FirstOrDefault(x => x.GroupId == groupId && x.SprintNumber == currentlyActiveSprint).SprintId;
                    var userStoryItem = db.UserStories.FirstOrDefault(x =>
                        x.Story.Equals(userStory.userStory) && x.StoryPoint == userStory.storyPoint &&
                        x.GroupId == userStory.groupId);
                    userStoryItem.SprintId = sprintId;
                }


                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool RemoveUserStoryFromSprint(UserStory userStory)
        {
            try
            {
                var storyId = userStory.storyId;
                var userStoryItem = db.UserStories.Find(storyId);
                userStoryItem.SprintId = -1;

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool AddTaskToUserStory(Tasks task)
        {
            try
            {
                var storyId = task.StoryId;
                var taskId = db.Tasks.Count() + 1;
                var message = task.TaskMessage;
                var completed = task.Completed;
                var storyPoints = task.StoryPoints;

                db.Add(new Task
                {
                    StoryId = storyId,
                    TaskMessage = message,
                    TaskId = taskId,
                    Completed = completed,
                    StoryPoint = storyPoints
                });

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool ChangeRole(GroupMember groupMember)
        {
            try
            {
                var role = groupMember.role;
                var groupMemberItem = db.GroupMembers.Find(groupMember.user.username);
                groupMemberItem.GroupRole = role;
                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public bool CreateMinutesOfMeeting(MeetingNotes note)
        {
            int tempId = note.SprintId;
            int tempActiveSprint;
            if (tempId == 0)
            {
                tempActiveSprint = db.SprintLists.Find(note.groupId).CurrentlyActiveSprint;
                tempId = db.Sprints
                    .FirstOrDefault(x => x.GroupId == note.groupId && x.SprintNumber == tempActiveSprint).SprintId;
            }

            try
            {
                bool exist = db.MeetingNoteses.Any(p => p.GroupId == note.groupId && p.SprintId == tempId);
                if (exist)
                {
                    var meetingNotesItem = db.MeetingNoteses.Find(note.groupId, tempId);
                    if (note.dailyMeeting!=null)
                        meetingNotesItem.DailyMeeting = note.dailyMeeting;
                    if (note.planningMeeting!=null)
                        meetingNotesItem.PlanningMeeting = note.planningMeeting;
                    if (note.retrospectiveMeeting!=null)
                        meetingNotesItem.RetrospectiveMeeting = note.retrospectiveMeeting;
                    if (note.reviewMeeting!=null)
                        meetingNotesItem.ReviewMeeting = note.reviewMeeting;
                }
                else
                {
//                    var currentlyActiveSprint = db.SprintLists.Find(note.groupId).CurrentlyActiveSprint;
//                    var sprintId = db.Sprints
//                        .FirstOrDefault(x => x.GroupId == note.groupId && x.SprintNumber == currentlyActiveSprint)
//                        .SprintId;
                    db.Add(new Models.MeetingNotes
                    {
                        DailyMeeting = note.dailyMeeting,
                        PlanningMeeting = note.planningMeeting,
                        RetrospectiveMeeting = note.retrospectiveMeeting,
                        ReviewMeeting = note.reviewMeeting,
                        GroupId = note.groupId ,
                        SprintId = tempId
                    });
                }

                db.SaveChanges();
                return true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                return false;
            }
        }

        public Common.ProductBacklog LoadProductBacklog(int groupId)
        {
            var exist = db.UserStories.Any(x => x.GroupId == groupId);
            if (exist)
            {
                var list = db.UserStories.Where(x => x.GroupId == groupId && x.SprintId == -1);
                Common.ProductBacklog productBacklog = new Common.ProductBacklog {groupId = groupId};
                foreach (var userStoryDb in list)
                {
                    var userStory = new UserStory();
                    userStory.storyId = userStoryDb.StoryId;
                    userStory.groupId = productBacklog.groupId;
                    userStory.completed = 0;
                    if (userStoryDb.Completed)
                    {
                        userStory.completed = 1;
                    }

                    userStory.currentlyActive = userStoryDb.CurrentlyActive;
                    userStory.storyPoint = userStoryDb.StoryPoint;
                    userStory.userStory = userStoryDb.Story;
                    productBacklog.addUserStory(userStory);
                }

                return productBacklog;
            }
            else
            {
                return null;
            }
        }

        public Session GetGroupInformation(string username)
        {
            Session session = new Session();
            var isGroupMember = db.GroupMembers.Any(x => x.Username.Equals(username));
            session.username = username;
            session.groupId = 0;
            if (!isGroupMember) return session;
            session.groupId = db.GroupMembers.Find(username).GroupId;
            session.groupRole = db.GroupMembers.Find(username).GroupRole;
            session.numberOfSprints = db.SprintLists.Find(db.GroupMembers.Find(username).GroupId).NumberOfSprints;
            return session;
        }

        public Sprint LoadSprintBacklog(int sprintNumber, int groupId)
        {
            Console.WriteLine("LOAD SPRINT BACKLOG");
            var sprintId =
                db.Sprints.FirstOrDefault(x => x.GroupId == groupId && x.SprintNumber == sprintNumber).SprintId;
            bool exist = db.UserStories.Any(x => x.SprintId == sprintId);
            if (exist)
            {
                Sprint sprint = new Sprint(sprintNumber);
                sprint.groupId = groupId;
                sprint.sprintId = sprintId;
                sprint.sprintNumber = sprintNumber;
                var list = db.UserStories.Where(x => x.SprintId == sprintId);
                foreach (var userStoryDb in list)
                {
                    var userStory = new UserStory();
                    userStory.storyId = userStoryDb.StoryId;
                    userStory.groupId = sprint.groupId;
                    userStory.completed = 0;
                    if (userStoryDb.Completed)
                    {
                        userStory.completed = 1;
                    }

                    userStory.currentlyActive = userStoryDb.CurrentlyActive;
                    userStory.storyPoint = userStoryDb.StoryPoint;
                    userStory.userStory = userStoryDb.Story;
                    sprint.AddUserStory(userStory);
                }

                return sprint;
            }
            else
            {
                Console.WriteLine("RETURN NULL?");
                return null;
            }
        }

        public Group LoadGroup(int groupId)
        {
            bool exist = db.GroupMembers.Any(x => x.GroupId == groupId);

            if (exist)
            {
                IQueryable<Models.GroupMember> list = db.GroupMembers.Where(x => x.GroupId == groupId);
                Group group = new Group {groupId = groupId};

                foreach (Models.GroupMember groupMemberDB in list)
                {
                    var groupMember = new GroupMember();
                    groupMember.user.username = groupMemberDB.Username;
                    groupMember.role = groupMemberDB.GroupRole;
                    groupMember.groupID = groupId;
                    group.AddMember(groupMember);
                }

                Console.WriteLine(group.groupMembers[0].role);
                return group;
            }

            return null;
        }

        public MeetingNotes LoadMeetingNotes(int groupId)
        {
            int tempId;
            int tempActiveSprint;
         
                tempActiveSprint = db.SprintLists.Find(groupId).CurrentlyActiveSprint;
                tempId = db.Sprints
                    .FirstOrDefault(x => x.GroupId == groupId && x.SprintNumber == tempActiveSprint).SprintId;

                var exist = db.MeetingNoteses.Any(x => x.GroupId == groupId&& x.SprintId == tempId);
            if (exist)
            {
                var meetingNoteDB = db.MeetingNoteses.Find(groupId,tempId);
                var meetingNotes = new MeetingNotes {groupId = groupId};
                meetingNotes.dailyMeeting = meetingNoteDB.DailyMeeting;
                meetingNotes.planningMeeting = meetingNoteDB.PlanningMeeting;
                meetingNotes.retrospectiveMeeting = meetingNoteDB.RetrospectiveMeeting;
                meetingNotes.reviewMeeting = meetingNoteDB.ReviewMeeting;
                meetingNotes.SprintId =tempId;
                return meetingNotes;
            }

            return null;
        }


        public UserStory LoadUserStory(int storyId)
        {
            var exist = db.Tasks.Any(x => x.StoryId == storyId);
            if (exist)
            {
                var list = db.Tasks.Where(x => x.StoryId == storyId);
                var userStory = new UserStory();
                userStory.storyId = storyId;

                foreach (var taskDB in list)
                {
                    var task = new Tasks();
                    task.TaskMessage = taskDB.TaskMessage;
                    task.TaskId = taskDB.TaskId;
                    task.StoryPoints = taskDB.StoryPoint;
                    task.StoryId = storyId;
                    task.Completed = taskDB.Completed;
                    userStory.addTask(task);
                }

                return userStory;
            }

            return null;
        }
    }
}