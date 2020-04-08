using System;
using System.Linq;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Hosting;
using WebApplication.Common;
using WebApplication.Database.Data;
using WebApplication.Database.Repository;

namespace WebApplication
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
            /*ScrumContext scrumContext = new ScrumContext();
            GroupRepository groupRepository = new GroupRepository(scrumContext);
            var usernames = groupRepository.GetAllUsernames();
            Console.WriteLine(usernames.Count());*/
          
          
        }

       public static IHostBuilder CreateHostBuilder(string[] args) =>
        Host.CreateDefaultBuilder(args)
            .ConfigureWebHostDefaults(webBuilder => { webBuilder.UseStartup<Startup>(); });
    
       


    }
    
}


