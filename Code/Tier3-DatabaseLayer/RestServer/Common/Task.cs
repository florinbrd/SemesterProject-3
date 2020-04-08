﻿using System;

 namespace WebApplication.Common
{
    public class Tasks
    {
        private int taskId;
        private string taskMessage;
        private int storyPoints;
        private bool completed;
        public bool validated { get; set; }
        public int StoryId { get; set; }

        public Tasks()
        {
        }

        public Tasks(string taskMessage, int storyPoints)
        {
            this.taskMessage = taskMessage;
            this.storyPoints = storyPoints;
            completed = false;
        }

        public string TaskMessage
        {
            get => taskMessage;
            set => taskMessage = value;
        }

        public int StoryPoints
        {
            get => storyPoints;
            set => storyPoints = value;
        }

        public bool Completed
        {
            get => completed;
            set => completed = value;
        }

        public void IsCompletedByAction()
        {
            completed = true;
        }

        public void IsCompleteByStoryPoints()
        {
            if (storyPoints == 0)
            {
                completed = true;
            }
            else
            {
                completed = false;
            }
        }

        public int TaskId
        {
            get => taskId;
            set => taskId = value;
        }

        public override string ToString()
        {
            return taskMessage + " " + storyPoints + " " + completed;
        }

        public override Boolean Equals(Object obj)
        {
            if (!(obj is Tasks))
            {
                return false;
            }

            Tasks other = (Tasks) obj;

            return taskMessage.Equals(other.taskMessage) && storyPoints == other.storyPoints &&
                   completed == other.completed;
        }
    }
}