using System.ComponentModel.DataAnnotations;

namespace WebApplication.Common
{
    public class MeetingNotes
    {
        public int groupId { get; set; }
        public string planningMeeting { get; set; }
        public string retrospectiveMeeting { get; set; }
        public string reviewMeeting { get; set; }
        public string dailyMeeting { get; set; }
        public int SprintId { get; set; }
        public bool validated { get; set; }
      

        public MeetingNotes()
        {
        }
        public MeetingNotes(string planningMeeting, string retrospectiveMeeting, string reviewMeeting, string dailyMeeting)
        {
            this.planningMeeting = planningMeeting;
            this.retrospectiveMeeting = retrospectiveMeeting;
            this.reviewMeeting = reviewMeeting;
            this.dailyMeeting = dailyMeeting;
        }

        
    }
}