using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class UserStory
    {
        //PK
        [Key]
        [RegularExpression(@"^\S*$")]
        [Required]
        public int StoryId { get; set; }
        
        [Required]
        public string Story { get; set; }
            
        [Required]
        public int StoryPoint { get; set; }
        
        [Required]
        public bool CurrentlyActive { get; set; }
        
        [Required]
        public bool Completed { get; set; }
        
        [Required]
        [RegularExpression(@"^\S*$")]
        public int SprintId { get; set; }
        
        public int GroupId { get; set; }
        
        public ICollection<Task> Tasks { get; set; }
        
    }
}