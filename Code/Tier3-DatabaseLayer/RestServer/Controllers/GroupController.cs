using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Common;
using Group = System.Text.RegularExpressions.Group;
using Validator = WebApplication.validation.Validator;

namespace WebApplication.Controllers
{
    [Route("api/[controller]")]
    public class GroupController : ControllerBase


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

        [HttpGet("{groupId:int}")]
        [Produces("application/json")]
        public IActionResult Get(int groupId)
        {
            Common.Group group = null;
            group = iDatabaseServer.loadGroup(groupId);

            Console.WriteLine("dsadsadddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                              group.groupMembers);

            return CreatedAtAction("Get", group);
        }

        // POST api/values
        [HttpPost]
        public IActionResult Post([FromBody] Common.Group group)
        {
            Console.WriteLine("---------------tested--------------------------" +
                              group.groupMembers[0].user.username);
            Console.WriteLine("------------------------------------" + group);
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (iDatabaseServer.CreateGroup(group))
            {
                group.validated = true;
                return CreatedAtAction("Post", group);
            }

            return CreatedAtAction("Post", group);
        }

        [HttpPost("{code:int}")]
        public IActionResult PostMember([FromBody] GroupMember groupMember, int code)
        {
            Console.WriteLine("---------------POSTMEMBER---------------------" + groupMember.role + " " +
                              groupMember.user.username+" "+groupMember.groupID);
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (code.Equals(0))
            {
                if (iDatabaseServer.AddMember(groupMember))
                {
                    groupMember = new GroupMember();
                    Console.WriteLine("---------------POSTMEMBER---------------------" + groupMember.role + " " +
                                      groupMember.user.username);
                    groupMember.validated = true;
                    return CreatedAtAction("Post", groupMember);
                }
            }

            if (code.Equals(1))
            {
                if (iDatabaseServer.changeGroupRole(groupMember))
                {
                    Console.WriteLine("---------------CHANGEROLE---------------------");
                    groupMember.validated = true;
                    return CreatedAtAction("Post", groupMember);
                }
            }


            return CreatedAtAction("Post", groupMember);
        }
//        [HttpPost]
//        public IActionResult Post([FromBody] GroupMember groupMember,int groupID)
//        {
//            if (!ModelState.IsValid)
//            {
//                return BadRequest(ModelState);
//            }
//
//            if (_validator.AddMember(groupMember,groupID))
//            {
//                return CreatedAtAction("Post", groupMember);
//            }
//            else
//                return BadRequest("Error");
//        }

        //PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/values/5
        [HttpDelete("{username}")]
        public void Delete(string username)
        {
            Console.WriteLine(username + " is deleted---------------------------------------------");
            GroupMember groupMember = new GroupMember();
            groupMember.user.username = username;
            Console.WriteLine(iDatabaseServer.RemoveMember(groupMember));
      
        }
    }
}