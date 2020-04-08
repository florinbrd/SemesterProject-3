using System;

namespace WebApplication.Common
{
    public class RequestWrapper
    {
        public Login obj1 { get; set; }
        public Session obj2 { get; set; }

        public RequestWrapper(Login obj1, Session obj2)
        {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        public RequestWrapper()
        {
        }
    }
    
}