using System.Collections.Generic;
using System.Linq;
using Microsoft.Data.Sqlite;
using Microsoft.EntityFrameworkCore;
using NUnit.Framework;
using WebApplication.Common;
using WebApplication.Database.Data;
using WebApplication.Database.Repository;
using User = WebApplication.Common.User;

namespace Test
{
    public class Tests
    {
        [Test]
        public void GetPassword()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    var password = service.GetPassword("dave");
                    Assert.AreEqual("admin1", password);
                }
            }
            finally
            {
                connection.Close();
            }
        }
        
        [Test]
        public void GetAllUsernames()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    var usernames = service.GetAllUsernames();
                    Assert.AreEqual(0, usernames.Count);
                }
            }
            finally
            {
                connection.Close();
            }
        }
        
        [Test]
        public void RemoveMember_AddMember()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    var user = new User{username = "Andrew"};
                    GroupMember groupMember = new GroupMember {user = user, role = "Developer", groupID = 1};
                    var added = service.AddMember(groupMember);
                    var existingItem = context.GroupMembers.Any(x => x.Username.Equals("Andrew"));
                    Assert.AreEqual(true, existingItem);
                    bool removed = service.RemoveMember(groupMember);
                    bool removedItem = context.GroupMembers.Any(x => x.Username.Equals("Andrew"));
                    Assert.AreEqual(false, removedItem);
                    Assert.AreEqual(true, removed);

                }
            }
            finally
            {
                connection.Close();
            }
        }
        
        [Test]
        public void CreateAccount()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    var oldValue = context.Users.Count();
                    var created = service.CreateAccount("florian", "admin3");
                    Assert.AreEqual(true, created);
                    Assert.AreEqual(oldValue+1, context.Users.Count());
                }
            }
            finally
            {
                connection.Close();
            }
        }
        
        [Test]
        public void CreateGroup()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    Group group = new Group();
                    GroupMember groupMember = new GroupMember();
                    groupMember.user.username = "dave";
                    groupMember.role = "Developer";

                    GroupMember groupMember2 = new GroupMember();
                    groupMember2.user.username = "Peter";
                    groupMember2.role = "Product Owner";
                    group.AddMember(groupMember);
                    group.AddMember(groupMember2);
                    
                    var created = service.CreateGroup(group);
                    Assert.AreEqual(true, created);
                    // Assert.AreEqual(2, context.GroupMembers.Count());
                    // Assert.AreEqual(2, context.GroupMembers.Find("Andrew").GroupId);
                }
            }
            finally
            {
                connection.Close();
            }
        }
        
        [Test]
        public void AddUserStory()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    UserStory userStory = new UserStory("user story message", 3, 0, 1);
                    bool added = service.AddStory(userStory);
                    Assert.AreEqual(true,added);
                    Assert.AreEqual(2, context.UserStories.Count());
                }
            }
            finally
            {
                connection.Close();
            }
        }
        
        [Test]
        public void SetNumberOfSprints()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    var sprintList = new SprintList();
                    sprintList.groupID = 1;
                    sprintList.numberOfSprints = 5;
                    var set = service.SetNumberOfSprints(sprintList);
                    // Assert.AreEqual(true, set);
                    // Assert.AreEqual(5, context.SprintLists.Find(1).NumberOfSprints);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void SetDurationOfSprints()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    SprintList sprintList = new SprintList(1);
                    sprintList.groupID = -1;
                    bool set = service.SetDurationOfSprints(sprintList);
                    Assert.AreEqual(true, set);
                    Assert.AreEqual(1, context.SprintLists.Find(-1).Duration);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void SetActiveSprint()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    SprintList sprintList = new SprintList();
                    sprintList.groupID = -1;
                    sprintList.currentlyActiveSprint = 1;
                    var set = service.SetActiveSprint(sprintList);
                    Assert.AreEqual(true, set);
                    Assert.AreEqual(1, context.SprintLists.Find(-1).CurrentlyActiveSprint);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void AddUserStoryToSprint()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    Sprint sprint = new Sprint(3);
                    sprint.sprintId = 1;
                    UserStory userStory = new UserStory("user story message", 3, 0, -1);
                    userStory.sprintId = 1;
                    userStory.storyId = 1;
                    bool added = service.AddUserStoryToSprint(userStory);
                    Assert.AreEqual(1, context.UserStories.Find(1).SprintId);
                    Assert.AreEqual(true, added);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void RemoveUserStoryFromSprint()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                { 
                    var service = new GroupRepository(context);
                    UserStory userStory = new UserStory("user story message", 3, 0, -1);
                    userStory.storyId = 1;
                    bool removed = service.RemoveUserStoryFromSprint(userStory);
                    Assert.AreEqual(true, removed);
                    Assert.AreEqual(-1, context.UserStories.Find(1).SprintId);

                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void AddTaskToUserStory()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    UserStory userStory = new UserStory("user story message", 3, 0, -1);
                    userStory.storyId = 1;
                    Tasks task = new Tasks("task message", 5);
                    task.TaskId = 1;
                    task.Completed = false;
                    bool added = service.AddTaskToUserStory(task);
                    Assert.AreEqual(true, added);
                    Assert.AreEqual(1, context.Tasks.Count());
                    Assert.AreEqual("task message", context.Tasks.Find(1).TaskMessage);

                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void ChangeRole()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    User user = new User();
                    user.username = "dave";
                    GroupMember groupMember = new GroupMember();
                    groupMember.user = user;
                    groupMember.role = "Product Owner";
                    groupMember.groupID = 1;
                    service.AddMember(groupMember);
                    Assert.AreEqual("Product Owner", context.GroupMembers.Find("dave").GroupRole);
                    groupMember.role = "Scrum Master";
                    service.ChangeRole(groupMember);
                    Assert.AreEqual("Scrum Master", context.GroupMembers.Find("dave").GroupRole);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void CreateMinutesOfMeeting()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    MeetingNotes notes = new MeetingNotes();
                    notes.SprintId = 1;
                    notes.dailyMeeting = "DAILY TEST";
                    bool created = service.CreateMinutesOfMeeting(notes);
                    Assert.AreEqual(true, created);
                    Assert.AreEqual(1, context.MeetingNoteses.Count());
                    Assert.AreEqual("DAILY TEST", context.MeetingNoteses.Find(1).DailyMeeting);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void LoadProductBacklog()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    UserStory userStory = new UserStory("user story message", 3, 0, 1);
                    bool added = service.AddStory(userStory);
                    Assert.AreEqual(true, added);
                    var PB = service.LoadProductBacklog(1);
                    var message= PB.userStories[1].userStory;
                    Assert.AreEqual(message, context.UserStories.Find(2).Story);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void LoadSprintBacklog()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    var sprint = service.LoadSprintBacklog(1, 1);
                    Assert.AreEqual(sprint.sprintId, context.UserStories.Find(1).SprintId);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void LoadGroup()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                  
                    GroupMember groupMember = new GroupMember();
                    groupMember.user.username = "jaime";
                    groupMember.groupID = 1;
                    groupMember.role = "Scrum Master";
                    var added = service.AddMember(groupMember);
                    var group = service.LoadGroup(1);
                    Assert.AreEqual(true, added);
                    Assert.AreEqual(1, context.GroupMembers.Count());
                    // Assert.AreEqual(group.GetGroupMembers()[0].user.username, context.GroupMembers.Find("jaime").Username);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
        [Test]
        public void GetGroupInformation()
        {
            // In-memory database only exists while the connection is open
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            try
            {
                var options = new DbContextOptionsBuilder<ScrumContext>()
                    .UseSqlite(connection)
                    .Options;

                // Create the schema in the database
                using (var context = new ScrumContext(options))
                {
                    context.Database.EnsureCreated();
                }

                // Run the test against one instance of the context
                using (var context = new ScrumContext(options))
                {
                    var service = new GroupRepository(context);
                    var session = service.GetGroupInformation("dave");
                    Assert.AreEqual(session.username, "dave");
                    Assert.AreEqual(session.groupId, 0);
                    Assert.AreEqual(session.groupRole, null);
                }
            }
            finally
            {
                connection.Close();
            }
        } 
        
    }
}