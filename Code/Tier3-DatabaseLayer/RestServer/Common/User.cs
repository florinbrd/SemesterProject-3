using System;

namespace WebApplication.Common
{
    public class User
    {
        public int id { get; set; }
        public Boolean validated { get; set; }
        public String username { get; set; }
        public String password { get; set; }


        public User()
        {
        }



        public User(string username, string password, int id)
        {
            this.id = id;
            this.password = password;
            validated = false;
            this.username = username;
        }






        

        public override string ToString()
        {
            return "User{" +
                   ", password='" + password + '\'' +
                   '}';
        }



    }
}