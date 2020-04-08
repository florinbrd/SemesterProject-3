using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class Group
    {
        [Key]
        [Required]
        [RegularExpression(@"^\S*$")]
        public int GroupId { get; set; }
        
        public SprintList SprintList { get; set; }
        public ProductBacklog ProductBacklog { get; set; }
        public ICollection<GroupMember> GroupMembers { get; set; }
    }
}