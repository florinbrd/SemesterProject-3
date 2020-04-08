using System.ComponentModel.DataAnnotations;

namespace WebApplication.Database.Models
{
    public class User
    {
        //PK
        [Key]
        [StringLength(18, MinimumLength = 3)]
        [Required]
        public string Username { get; set; }
        
        [StringLength(18, MinimumLength = 3)]
        [Required]
        public string Password { get; set; }

        

    }
}