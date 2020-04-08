using System;
using System.Collections.Generic;

namespace WebApplication.Common
{
    public class UserStory
    {
        public int storyId{ get; set; }
        public int storyPoint{ get; set; }
        public string userStory{ get; set; }
        public bool currentlyActive{ get; set; }
        public int completed{ get; set; }
        public  List<Tasks> taskList{ get; set; }
        public int sprintId { get; set; }
        public int groupId { get; set; }
        public bool validated { get; set; }
        public UserStory()
        {
            taskList = new List<Tasks>();
        }

        

        public UserStory(string userStory, int storyPoint, int completed, int groupId)
        {
            
            this.userStory = userStory;
            this.storyPoint = storyPoint;
            this.completed = completed;
            currentlyActive = false;
            taskList = new List<Tasks>();
            this.groupId = groupId;
        }
        

        public void SetCurrentlyActiveByCompleted()
        {
            if (completed == 1)
            {
                currentlyActive = false;
            }
            else if (completed == 0)
            {
                currentlyActive = true;
            }
        }

        public int GetTasksSize()
        {
            return taskList.Count;
        }

        public void SetTasks(Tasks task)
        {
            taskList.Add(task);
        }

        public void RemoveTaskByIndex(int index)
        {
            taskList.RemoveAt(index);
        }

        public void RemoveTaskbyTask(Tasks e)
        {
            taskList.Remove(e);
        }

        public List<Tasks> GetAllTasks()
        {
            return taskList;
        }

        public void addTask(Tasks tasks)
        {
            taskList.Add(tasks);
        }

   

        public override String ToString()
        {
            return taskList + userStory + " " + storyPoint + " " + currentlyActive + " " + completed;
        }
    }
}