using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Common;
using WebApplication.validation;

namespace WebApplication.Controllers
{
    [Route("api/[controller]")]
    public class NotesController : ControllerBase


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

        [HttpGet("{meetingId:int}")]
        [Produces("application/json")]
        public IActionResult Get(int meetingId)
        {
            MeetingNotes notes = iDatabaseServer.loadMeetingNotes(meetingId);
            if (notes != null)
            {
                notes.validated = true;
                return CreatedAtAction("Get", notes);
            }

            Console.WriteLine("Meeting notes not validated");
            return CreatedAtAction("Get", new MeetingNotes());
        }

        // POST api/values
        [HttpPost]
        public IActionResult Post([FromBody] MeetingNotes notes)
        {
            Console.WriteLine("-------------Notes Controller Post note method------------------- ");
            
            Console.WriteLine("------------------------"+notes.dailyMeeting);

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (iDatabaseServer.addMeetingNotes(notes))
            {
                notes.validated = true;
                return CreatedAtAction("Post", notes);
            }

            return CreatedAtAction("Post", notes);
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