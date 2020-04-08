using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class Task
    {
        [Key]
        [Required]
        [RegularExpression(@"^\S*$")]
        public int TaskId { get; set; }
        
        [Required]
        public string TaskMessage { get; set; }
        
        [Required]
        public bool Completed { get; set; }
        
        [Required]
        public int StoryPoint { get; set; }
        
        [Required]
        [RegularExpression(@"^\S*$")]
        public int StoryId { get; set; }
    }
}