using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using ScrumTool.Data;
using ScrumTool.Models;

namespace ScrumTool
{
    public class Program
    {
        public static void Main(string[] args)
        {
            using (var db = new ScrumContext())
            {
                db.Add(new Group { GroupId = 9});
                Console.WriteLine("Creating group");

                try
                {
                    db.Add(new User
                        {GroupId = 1, Password = "5", Username = "idk", GroupRole = GroupRole.ScrumMaster});
                    db.SaveChanges();
                    Console.Write("Creating user");
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                }
               
            }

        }
    }
}