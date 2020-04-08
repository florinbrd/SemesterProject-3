using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Common;

namespace WebApplication.Controllers

{
    namespace Tier2.RestServer.Controllers
    {
        [Route("api/[controller]")]
        public class UserStoryController : ControllerBase

        {
            private IDatabaseServer iDatabaseServer = new DatabaseIMPL();

            // GET: api/values
            [HttpGet]
            [Produces("application/json")]
            public IEnumerable<String> Get()
            {
                return new string[] {"value1", "value2"};
            }
            //GET: api/values/5

            [HttpGet("{storyId:int}")]
            [Produces("application/json")]
            public IActionResult Get(int storyId)
            {
                UserStory userStory =  iDatabaseServer.loadTasks(storyId);
                if (userStory != null)
                {
                    userStory.validated = true;
                }
                else
                {
                    Console.WriteLine("null from database");
                    userStory = new UserStory();
                }

                return CreatedAtAction("Get", userStory);
            }

            // Adds task to a specific userstory
            [HttpPost]
            public IActionResult Post([FromBody] Tasks task)
            {
                Console.WriteLine("-------------------------"+task.TaskMessage);
                if (!ModelState.IsValid)
                {
                    return BadRequest(ModelState);
                }


                if (iDatabaseServer.addTaskToUserStory(task))
                {
                    task.validated = true;
                    return CreatedAtAction("Post", task);
                }


                return CreatedAtAction("Post", task);
            }


            // DELETE api/values/5
            [
                HttpDelete]
            public void Delete([FromBody] UserStory userStory)
            {
            }
        }
    }
}