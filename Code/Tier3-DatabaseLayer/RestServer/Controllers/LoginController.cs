using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Common;
using WebApplication.validation;

namespace WebApplication.Controllers
{
    [Route("api/[controller]")]
    public class LoginController : ControllerBase


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

        [HttpGet("{username}")]
        [Produces("application/json")]
        public IActionResult Get(string username)
        {
            Console.WriteLine("Get string username");
            return Ok(new {Name = username});
        }

        // POST api/values
        [HttpPost]
        public IActionResult Post([FromBody] RequestWrapper wrapper)
        {
            Login login = wrapper.obj1;
            Console.WriteLine("-------------Login Controller Post Login method------------------- username: " +
                              login.username + ", password: " + login.password);

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (iDatabaseServer.GetPassword(login.username).Equals(login.password))
            {
                RequestWrapper w = new RequestWrapper();
                Session session = iDatabaseServer.getSession(login.username);
                session.validated = true;
                w.obj2 = session;

                return CreatedAtAction("Post", w);
            }

            RequestWrapper falseR = new RequestWrapper();
            Session falseSession = new Session();
            falseSession.validated = false;
            falseR.obj2 = falseSession;

            return CreatedAtAction("Post", falseR);
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