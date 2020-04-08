using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Common;
using WebApplication.validation;


namespace WebApplication.Controllers
{
    [Route("api/[controller]")]
    public class UserController : ControllerBase


    {
        private IDatabaseServer database = new DatabaseIMPL();

        // GET: api/values
        [HttpGet]
        [Produces("application/json")]
        public IActionResult Get()
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            return Ok(database.GetAllUsernames());
        }
        //GET: api/values/5

        [HttpGet("{username}")]
        [Produces("application/json")]
        public IActionResult Get(string username)
        {

            Session session = database.getSession(username);
            session.validated = true;
            return CreatedAtAction("Get", session);
        }

        // POST api/values
        [HttpPost]
        public IActionResult Post([FromBody] User user)
        {
            Console.WriteLine("-------------User Controller Post User method-------------------" + user.username + " " +
                              user.password);

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (database.CreateAccount(user))
            {
                Console.WriteLine(user.ToString());
                user.validated = true;
                return CreatedAtAction("Post", user);
            }

            return CreatedAtAction("Post", user);
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