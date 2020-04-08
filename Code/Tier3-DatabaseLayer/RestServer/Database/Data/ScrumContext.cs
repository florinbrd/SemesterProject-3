﻿﻿﻿using Microsoft.EntityFrameworkCore;
   using Microsoft.Extensions.Logging;
   using Microsoft.Extensions.Logging.Console;
   using SQLitePCL;
   using WebApplication.Common;
   using WebApplication.Database.Models;
   using Group = WebApplication.Database.Models.Group;
   using GroupMember = WebApplication.Database.Models.GroupMember;
   using MeetingNotes = WebApplication.Database.Models.MeetingNotes;
   using ProductBacklog = WebApplication.Database.Models.ProductBacklog;
   using Sprint = WebApplication.Database.Models.Sprint;
   using SprintList = WebApplication.Database.Models.SprintList;
   using User = WebApplication.Database.Models.User;
   using UserStory = WebApplication.Database.Models.UserStory;

   namespace WebApplication.Database.Data
{
    public class ScrumContext: DbContext
    {
    
        public ScrumContext()
        {
        }

        public ScrumContext(DbContextOptions<ScrumContext> options) : base(options)
        {
            Database.EnsureCreated();
            
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseLoggerFactory(new LoggerFactory()).EnableSensitiveDataLogging();
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseSqlite("Data Source=database.db");
            }
            
   
            
        }
        

        public DbSet<Group> Groups { get; set; }
        public DbSet<GroupMember> GroupMembers { get; set; }
        public DbSet<MeetingNotes> MeetingNoteses { get; set; }
        public DbSet<ProductBacklog> ProductBacklogs { get; set; }
        public DbSet<Sprint> Sprints { get; set; }
        public DbSet<SprintList> SprintLists { get; set; }
        public DbSet<Task> Tasks { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<UserStory> UserStories { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            
            modelBuilder.Entity<Group>().ToTable("Group");
            modelBuilder.Entity<GroupMember>().ToTable("GroupMember");
            modelBuilder.Entity<MeetingNotes>().ToTable("MeetingNote");
            modelBuilder.Entity<ProductBacklog>().ToTable("ProductBacklog");
            modelBuilder.Entity<Sprint>().ToTable("Sprint");
            modelBuilder.Entity<SprintList>().ToTable("SprintList");
            modelBuilder.Entity<Task>().ToTable("Task");
            modelBuilder.Entity<User>().ToTable("User");
            modelBuilder.Entity<UserStory>().ToTable("UserStory");
            
            modelBuilder.Entity<MeetingNotes>()
                .HasKey(c => new { c.GroupId, c.SprintId });

            
            

            modelBuilder.Entity<Group>()
                .HasData(
                    new Group
                    {
                        GroupId = -1
                    },
                    new Group
                    {
                        GroupId = 1
                    }
                );
            modelBuilder.Entity<User>()
                .HasData(
                    new User
                    {
                        Username = "dave",
                        Password = "admin1"
                    },
                    new User {
                        Username = "jaime",
                        Password = "admin2"
                        }
                );
            modelBuilder.Entity<ProductBacklog>()
                .HasData(
                    new ProductBacklog
                    {
                        GroupId = -1
                    }
                );
            modelBuilder.Entity<SprintList>()
                .HasData(
                    new SprintList
                    {
                        GroupId = -1,
                        Duration = 0,
                        NumberOfSprints = 0,
                        CurrentlyActiveSprint = 0
                    },
                    new SprintList
                    {
                        GroupId = 1,
                        Duration = 0,
                        NumberOfSprints = 0,
                        CurrentlyActiveSprint = 0
                    }
                );
            modelBuilder.Entity<Sprint>()
                .HasData(
                    new Sprint
                    {
                        SprintId = 1,
                        SprintNumber = 2,
                        GroupId = 1
                    },
                    new Sprint
                    {
                        SprintId = -1,
                        SprintNumber = 1,
                        GroupId = 1
                    });
            modelBuilder.Entity<UserStory>()
                .HasData(
                    new UserStory
                    {
                        StoryId = 1,
                        Story = "As a role I want smth to goal",
                        StoryPoint = 3,
                        CurrentlyActive = false,
                        Completed = false,
                        GroupId = 1,
                        SprintId = -1
                    }
                );
        }
    }
}