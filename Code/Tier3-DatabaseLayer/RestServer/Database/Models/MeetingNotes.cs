using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class MeetingNotes
    {
        [Key]
        [Required]
        public int GroupId { get; set; }
        public string PlanningMeeting { get; set; }
        public string DailyMeeting { get; set; }
        public string ReviewMeeting { get; set; }
        public string RetrospectiveMeeting { get; set; }
        [Key]
        public int SprintId { get; set; }
    }
}