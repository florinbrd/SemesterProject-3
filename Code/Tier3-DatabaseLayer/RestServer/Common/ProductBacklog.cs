using System.Collections.Generic;

namespace WebApplication.Common
{
    public class ProductBacklog
    {

        public int groupId { get; set; }
        public List<UserStory> userStories { get; set; }

      

        public ProductBacklog()
        {
            userStories = new List<UserStory>();
        }

        public void removeUserStory(UserStory e)
        {
            userStories.Remove(e);
        }

        public void removeUserStoryByIndex(int index)
        {
            userStories.Remove(userStories[index]);
        }

        public void addUserStory(UserStory e)
        {
            userStories.Add(e);
        }
        
    }
}

    