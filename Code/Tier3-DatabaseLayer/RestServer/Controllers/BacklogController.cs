using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Web.Helpers;
using WebApplication.Common;
using WebApplication.validation;


namespace WebApplication.Controllers
{
    [Route("api/[controller]")]
    public class BacklogController : ControllerBase

    {
        private IDatabaseServer databaseServer = new DatabaseIMPL();

        [HttpGet("{groupID:int}")]
        [Produces("application/json")]
        public IActionResult Get(int groupID)
        {
            ProductBacklog productBacklog = null;
            Console.WriteLine("------------------"+groupID);
            productBacklog = databaseServer.loadProductBacklog(groupID);
//           Console.WriteLine(productBacklog.userStories[0].userStory);
            return CreatedAtAction("Post", productBacklog);
        }

        // POST api/values
        [HttpPost]
        [Produces("application/json")]
        public IActionResult Post([FromBody] UserStory story)
        {
            Console.WriteLine("-----------------------POSTSTORY----------!"+story.userStory+" "+story.storyPoint+ " id: "+story.groupId);
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            
            

            if (databaseServer.AddStory(story))
            {
                Console.WriteLine("-----------------------POSTSTORY----------validated!");
                story.validated = true;
                return CreatedAtAction("Post", story);
            }

            return CreatedAtAction("Post", story);
        }

        //PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/values/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}