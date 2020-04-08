using System.Collections.Generic;

namespace WebApplication.Common
{
    public class SprintList

    {
        public int duration{ get; set; }
        public List<Sprint> sprintsList{ get; set; }
        public int numberOfSprints{ get; set; }
        public int groupID{ get; set; }
        public int currentlyActiveSprint{ get; set; }
        public bool validated { get; set; }

 
        public SprintList()
        {
        }

        public SprintList(int duration)
        {
            this.duration = duration;
            sprintsList = new List<Sprint>();
        }

   
      
    }
}