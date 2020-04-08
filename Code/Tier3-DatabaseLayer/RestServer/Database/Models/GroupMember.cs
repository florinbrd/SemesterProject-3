using System;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class GroupMember
    {
        [Key]
        [Required]
        public string Username { get; set; }

        public string GroupRole { get; set; }
        
        [Required]
        [RegularExpression(@"^\S*$")]
        public int GroupId { get; set; }
        
        public User User { get; set; }
    }
}