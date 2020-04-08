using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class ProductBacklog
    {
        [Key]
        [Required]
        [RegularExpression(@"^\S*$")]
        public int GroupId { get; set; }
        
        

        public ICollection<UserStory> UserStories { get; set; }
    }
}