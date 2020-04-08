using System;
using System.Collections.Generic;

namespace WebApplication.Common
{
    public class Group
    {
        public SprintList sprintList { get; set; }
        public ProductBacklog productBacklog { get; set; }
        public List<GroupMember> groupMembers { get; set; }
        public int groupId { get; set; }
        public bool validated { get; set; }

        public Group()
        {
            sprintList = new SprintList();
            productBacklog = new ProductBacklog();
            groupMembers = new List<GroupMember>();
        }

        public Group(int id)
        {
            sprintList = new SprintList();
            productBacklog = new ProductBacklog();
            groupId = id;
            groupMembers = new List<GroupMember>();
        }

        public void AddMember(GroupMember e)
        {
            groupMembers.Add(e);
        }

        public void RemoveMemberByMember(GroupMember e)
        {
            groupMembers.Remove(e);
        }

        public void RemoveGroupMemberByIndex(int index)
        {
            groupMembers.RemoveAt(index);
        }
        
        public override string ToString()
        {
            return
                         $"SprintList: {sprintList}, ProductBacklog: {productBacklog}, GroupMembers: {groupMembers}, GroupId: {groupId}, validated: {validated}";
                 }
    }
}