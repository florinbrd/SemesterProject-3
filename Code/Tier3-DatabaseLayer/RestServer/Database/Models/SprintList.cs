using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class SprintList
    {
        [Key]
        [RegularExpression(@"^\S*$")]
        public int GroupId { get; set; }
        
        public int Duration { get; set; }

        public int NumberOfSprints { get; set; }
        
        public int CurrentlyActiveSprint { get; set; }
        
        public ICollection<Sprint> Sprints { get; set; }
    }
}