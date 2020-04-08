namespace WebApplication.Common
{
    public class Session
    {
        public int groupId { get; set; }
        public string groupRole { get; set; }
        public string username { get; set; }
        public bool validated { get; set; }
        public int numberOfSprints { get; set; }
        public int activeSprint { get; set; }


        public Session()
        {

        }

        public Session(int groupId, string groupRole, string username)
        {
            this.groupId = groupId;
            this.groupRole = groupRole;
            this.username = username;
        }
    }
}

     