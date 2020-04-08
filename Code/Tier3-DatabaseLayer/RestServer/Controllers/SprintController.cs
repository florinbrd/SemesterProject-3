using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Common;

namespace WebApplication.Controllers

{
    namespace Tier2.RestServer.Controllers
    {
        [Route("api/[controller]")]
        public class SprintController : ControllerBase

        {
            private IDatabaseServer iDatabaseServer = new DatabaseIMPL();

            // GET: api/values
            [Microsoft.AspNetCore.Mvc.HttpGet]
            [Produces("application/json")]
            public IEnumerable<String> Get()
            {
                return new string[] {"value1", "value2"};
            }
            //GET: api/values/5

            [HttpGet("{username}")]
            [Produces("application/json")]
            public IActionResult Get(string username)
            {
                Console.WriteLine("Get string username");
                return Ok(new {Name = username});
            }

            // POST api/values
            [HttpPost]
            public IActionResult Post([FromBody] UserStory userStory)
            {
                if (!ModelState.IsValid)
                {
                    return BadRequest(ModelState);
                }


                if (iDatabaseServer.addUserStoryToSprint(userStory))
                {
                    return CreatedAtAction("Post", userStory);
                }


                return BadRequest("Error");
            }


            // DELETE api/values/5
            [
                HttpDelete]
            public void Delete([FromBody] UserStory userStory)
            {
                iDatabaseServer.removeUserStoryFromSprint(userStory);
            }
        }
    }
}