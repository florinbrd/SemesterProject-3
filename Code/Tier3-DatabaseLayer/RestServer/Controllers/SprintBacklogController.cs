using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Common;
using Group = System.Text.RegularExpressions.Group;
using Validator = WebApplication.validation.Validator;

namespace WebApplication.Controllers
{
    [Route("api/[controller]")]
    public class SprintBacklogController : ControllerBase


    {
        private IDatabaseServer iDatabaseServer = new DatabaseIMPL();

        // GET: api/values
        [HttpGet("{sprintNumber:int}/{groupId:int}")]
        [Produces("application/json")]
        public IActionResult Get(int sprintNumber, int groupId)
        {
            Console.WriteLine("---------------------------" + sprintNumber + " " + groupId);
            Sprint sprint;
            sprint = iDatabaseServer.loadSprintBacklog(sprintNumber, groupId);

            return CreatedAtAction("Get", sprint);
        }

        [HttpPost ("{operation:int}/{branch:int}")]
        [Produces("application/json")]
        public IActionResult Post([FromBody] UserStory story,int operation)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (operation.Equals(0))
            {
                Console.WriteLine("-----------------------POSTSTORY----------!" + story.userStory + " " + story.storyPoint);
           


                if (iDatabaseServer.addUserStoryToSprint(story))
                {
                    Console.WriteLine("-----------------------POSTSTORY----------validated!");
                    story.validated = true;
                    return CreatedAtAction("Post", story);
                }
            }
            if (operation.Equals(1))
            {
                Console.WriteLine("-----------------------swap story----------!" + story.userStory + " " + story.storyPoint);
           


                if (iDatabaseServer.removeUserStoryFromSprint(story))
                {
                    Console.WriteLine("-----------------------swap story----------validated!");
                    story.validated = true;
                    return CreatedAtAction("Post", story);
                }
            }
          

            return CreatedAtAction("Post", story);
        }


        [HttpPost("{code:int}")]
        public IActionResult PostCode([FromBody] SprintList sprintList, int code)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (code.Equals(0))
            {
                Console.WriteLine(sprintList.numberOfSprints);
                if (iDatabaseServer.setSprintNumber(sprintList))
                    sprintList.validated = true;
                return CreatedAtAction("PostCode", sprintList);
            }

            if (code.Equals(1))
            {
                Console.WriteLine(sprintList.duration);
                if (iDatabaseServer.setSprintDuration(sprintList))

                    sprintList.validated = true;

                return CreatedAtAction("PostCode", sprintList);
            }
            

            return CreatedAtAction("PostCode", sprintList);
        }

        //PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/values/5
        [HttpDelete]
        public void Delete(UserStory userStory)
        {
            Console.WriteLine(userStory.storyId+" ,"+userStory.userStory);
     
        }
    }
}