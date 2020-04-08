using Microsoft.EntityFrameworkCore.Migrations;

namespace WebApplication.Migrations
{
    public partial class FixUserGroupMember : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_User_GroupMember_GroupMemberUsername",
                table: "User");

            migrationBuilder.DropIndex(
                name: "IX_User_GroupMemberUsername",
                table: "User");

            migrationBuilder.DropColumn(
                name: "GroupMemberUsername",
                table: "User");

            migrationBuilder.AddColumn<string>(
                name: "Username1",
                table: "GroupMember",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_GroupMember_Username1",
                table: "GroupMember",
                column: "Username1");

            migrationBuilder.AddForeignKey(
                name: "FK_GroupMember_User_Username1",
                table: "GroupMember",
                column: "Username1",
                principalTable: "User",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_GroupMember_User_Username1",
                table: "GroupMember");

            migrationBuilder.DropIndex(
                name: "IX_GroupMember_Username1",
                table: "GroupMember");

            migrationBuilder.DropColumn(
                name: "Username1",
                table: "GroupMember");

            migrationBuilder.AddColumn<string>(
                name: "GroupMemberUsername",
                table: "User",
                type: "TEXT",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_User_GroupMemberUsername",
                table: "User",
                column: "GroupMemberUsername");

            migrationBuilder.AddForeignKey(
                name: "FK_User_GroupMember_GroupMemberUsername",
                table: "User",
                column: "GroupMemberUsername",
                principalTable: "GroupMember",
                principalColumn: "Username",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
