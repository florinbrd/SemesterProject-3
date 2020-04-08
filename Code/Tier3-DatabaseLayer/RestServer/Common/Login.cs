﻿using System;

 namespace WebApplication.Common
{
    public class Login
    {
        public Login()
        {
        }

        public Login(string username, string password)
        {
            this.username = username;
            this.password = password;
            validated = false;
        }

        public string username { get; set; }
        public string password { get; set; }
        public Boolean validated { get; set; }

        
    }
}