﻿using System;

 namespace WebApplication.Common
{
    public class GroupMember
    {
        public User user { get; set; }
        public String role { get; set; }
        public int groupID { get; set; }
        public Boolean validated { get; set; }
        public String username { get; set; }

        public GroupMember(User user){
            this.user = user;
        }

        public GroupMember()
        {
            user = new User();
        }

        public GroupMember(string username, string groupRole, int groupId)
        {
            user.username = username;
            role = groupRole;
            groupID = groupId;
        }


    }
}