using System;
using System.Collections.Generic;

namespace WebApplication.Common
{
    public class Sprint
    {
        public int sprintId { get; set; }
        public int sprintNumber { get; set; }
        public List<UserStory> sprintStories { get; set; }
        public bool completed { get; set; }
        public int groupId { get; set; }
        public MeetingNotes meetingNotes { get; set; }
        

        public Sprint(int sprintNumber){
            this.sprintNumber = sprintNumber;
            sprintStories = new List<UserStory>();
            completed = false;
        }

        public Sprint()
        {
        }


        public int GetNoOfUserStories()
        {
            return sprintStories.Count;
        }

        public void AddUserStory(UserStory userStory){
            sprintStories.Add(userStory);
        }

        public void SetCompletedByAction(){
            completed = true;
        }

        public void SetCompletedByPoints(){
            for(var i=0; i< sprintStories.Count; i++){
                if(sprintStories[i].storyPoint == 0){
                    completed = true;
                }
            }
        }

      
    }
}