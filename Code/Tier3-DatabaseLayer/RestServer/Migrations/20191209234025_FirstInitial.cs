using Microsoft.EntityFrameworkCore.Migrations;

namespace WebApplication.Migrations
{
    public partial class FirstInitial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Group",
                columns: table => new
                {
                    GroupId = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Group", x => x.GroupId);
                });

            migrationBuilder.CreateTable(
                name: "GroupMember",
                columns: table => new
                {
                    Username = table.Column<string>(nullable: false),
                    GroupRole = table.Column<string>(nullable: true),
                    GroupId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_GroupMember", x => x.Username);
                    table.ForeignKey(
                        name: "FK_GroupMember_Group_GroupId",
                        column: x => x.GroupId,
                        principalTable: "Group",
                        principalColumn: "GroupId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "ProductBacklog",
                columns: table => new
                {
                    GroupId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_ProductBacklog", x => x.GroupId);
                    table.ForeignKey(
                        name: "FK_ProductBacklog_Group_GroupId",
                        column: x => x.GroupId,
                        principalTable: "Group",
                        principalColumn: "GroupId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "SprintList",
                columns: table => new
                {
                    GroupId = table.Column<int>(nullable: false),
                    Duration = table.Column<int>(nullable: false),
                    NumberOfSprints = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SprintList", x => x.GroupId);
                    table.ForeignKey(
                        name: "FK_SprintList_Group_GroupId",
                        column: x => x.GroupId,
                        principalTable: "Group",
                        principalColumn: "GroupId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "User",
                columns: table => new
                {
                    Username = table.Column<string>(maxLength: 18, nullable: false),
                    Password = table.Column<string>(maxLength: 18, nullable: false),
                    GroupMemberUsername = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_User", x => x.Username);
                    table.ForeignKey(
                        name: "FK_User_GroupMember_GroupMemberUsername",
                        column: x => x.GroupMemberUsername,
                        principalTable: "GroupMember",
                        principalColumn: "Username",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "Sprint",
                columns: table => new
                {
                    SprintId = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    SprintNumber = table.Column<int>(nullable: false),
                    GroupId = table.Column<int>(nullable: false),
                    SprintListGroupId = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Sprint", x => x.SprintId);
                    table.ForeignKey(
                        name: "FK_Sprint_SprintList_SprintListGroupId",
                        column: x => x.SprintListGroupId,
                        principalTable: "SprintList",
                        principalColumn: "GroupId",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.CreateTable(
                name: "MeetingNote",
                columns: table => new
                {
                    MeetingNotesId = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    PlanningMeeting = table.Column<string>(nullable: true),
                    DailyMeeting = table.Column<string>(nullable: true),
                    ReviewMeeting = table.Column<string>(nullable: true),
                    RetrospectiveMeeting = table.Column<string>(nullable: true),
                    SprintId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_MeetingNote", x => x.MeetingNotesId);
                    table.ForeignKey(
                        name: "FK_MeetingNote_Sprint_SprintId",
                        column: x => x.SprintId,
                        principalTable: "Sprint",
                        principalColumn: "SprintId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "UserStory",
                columns: table => new
                {
                    StoryId = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    Story = table.Column<string>(nullable: false),
                    StoryPoint = table.Column<int>(nullable: false),
                    CurrentlyActive = table.Column<bool>(nullable: false),
                    Completed = table.Column<bool>(nullable: false),
                    SprintId = table.Column<int>(nullable: false),
                    GroupId = table.Column<int>(nullable: false),
                    ProductBacklogGroupId = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_UserStory", x => x.StoryId);
                    table.ForeignKey(
                        name: "FK_UserStory_ProductBacklog_ProductBacklogGroupId",
                        column: x => x.ProductBacklogGroupId,
                        principalTable: "ProductBacklog",
                        principalColumn: "GroupId",
                        onDelete: ReferentialAction.Restrict);
                    table.ForeignKey(
                        name: "FK_UserStory_Sprint_SprintId",
                        column: x => x.SprintId,
                        principalTable: "Sprint",
                        principalColumn: "SprintId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Task",
                columns: table => new
                {
                    TaskId = table.Column<int>(nullable: false)
                        .Annotation("Sqlite:Autoincrement", true),
                    TaskMessage = table.Column<string>(nullable: false),
                    Completed = table.Column<bool>(nullable: false),
                    StoryPoint = table.Column<int>(nullable: false),
                    StoryId = table.Column<int>(nullable: false),
                    UserStoryStoryId = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Task", x => x.TaskId);
                    table.ForeignKey(
                        name: "FK_Task_UserStory_UserStoryStoryId",
                        column: x => x.UserStoryStoryId,
                        principalTable: "UserStory",
                        principalColumn: "StoryId",
                        onDelete: ReferentialAction.Restrict);
                });

            migrationBuilder.InsertData(
                table: "Group",
                column: "GroupId",
                value: -1);

            migrationBuilder.InsertData(
                table: "Group",
                column: "GroupId",
                value: 1);

            migrationBuilder.InsertData(
                table: "Sprint",
                columns: new[] { "SprintId", "GroupId", "SprintListGroupId", "SprintNumber" },
                values: new object[] { 1, 1, null, 1 });

            migrationBuilder.InsertData(
                table: "Sprint",
                columns: new[] { "SprintId", "GroupId", "SprintListGroupId", "SprintNumber" },
                values: new object[] { -1, -1, null, 1 });

            migrationBuilder.InsertData(
                table: "User",
                columns: new[] { "Username", "GroupMemberUsername", "Password" },
                values: new object[] { "dave", null, "admin1" });

            migrationBuilder.InsertData(
                table: "User",
                columns: new[] { "Username", "GroupMemberUsername", "Password" },
                values: new object[] { "jaime", null, "admin2" });

            migrationBuilder.InsertData(
                table: "ProductBacklog",
                column: "GroupId",
                value: -1);

            migrationBuilder.InsertData(
                table: "SprintList",
                columns: new[] { "GroupId", "Duration", "NumberOfSprints" },
                values: new object[] { -1, 0, 0 });

            migrationBuilder.InsertData(
                table: "UserStory",
                columns: new[] { "StoryId", "Completed", "CurrentlyActive", "GroupId", "ProductBacklogGroupId", "SprintId", "Story", "StoryPoint" },
                values: new object[] { 1, false, false, 1, null, -1, "As a role I want smth to goal", 3 });

            migrationBuilder.CreateIndex(
                name: "IX_GroupMember_GroupId",
                table: "GroupMember",
                column: "GroupId");

            migrationBuilder.CreateIndex(
                name: "IX_MeetingNote_SprintId",
                table: "MeetingNote",
                column: "SprintId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Sprint_SprintListGroupId",
                table: "Sprint",
                column: "SprintListGroupId");

            migrationBuilder.CreateIndex(
                name: "IX_Task_UserStoryStoryId",
                table: "Task",
                column: "UserStoryStoryId");

            migrationBuilder.CreateIndex(
                name: "IX_User_GroupMemberUsername",
                table: "User",
                column: "GroupMemberUsername");

            migrationBuilder.CreateIndex(
                name: "IX_UserStory_ProductBacklogGroupId",
                table: "UserStory",
                column: "ProductBacklogGroupId");

            migrationBuilder.CreateIndex(
                name: "IX_UserStory_SprintId",
                table: "UserStory",
                column: "SprintId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "MeetingNote");

            migrationBuilder.DropTable(
                name: "Task");

            migrationBuilder.DropTable(
                name: "User");

            migrationBuilder.DropTable(
                name: "UserStory");

            migrationBuilder.DropTable(
                name: "GroupMember");

            migrationBuilder.DropTable(
                name: "ProductBacklog");

            migrationBuilder.DropTable(
                name: "Sprint");

            migrationBuilder.DropTable(
                name: "SprintList");

            migrationBuilder.DropTable(
                name: "Group");
        }
    }
}
