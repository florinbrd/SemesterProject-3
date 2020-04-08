using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class Sprint
    {
        [Key]
        [Required]
        [RegularExpression(@"^\S*$")]
        public int SprintId { get; set; }
        
        [Required]
        public int SprintNumber { get; set; }
        
        [Required]
        [RegularExpression(@"^\S*$")]
        public int GroupId { get; set; }
        
        public ICollection<UserStory> UserStories { get; set; }
        public MeetingNotes MeetingNotes { get; set; }
    }
}