﻿namespace WebApplication.Common
{
    public interface IDataModel
    {
        void AddUserStory(UserStory e);
        void RemoveUserStory(UserStory e);

        void Login(string username, string password);

        void CreateAccount(string username, string password, string passwordConfirm);
    }
}