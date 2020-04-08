using System;

namespace WebApplication.Common
{
    public class HttpResponse
    {
        public Boolean dbConfirmation { get; set; }
        public string dbErrorString { get; set; }

        public HttpResponse()
        {
            dbConfirmation = false;
            dbErrorString = "";
        }
        
        public HttpResponse(Boolean dbConfirmation)
        {
            this.dbConfirmation = dbConfirmation;
            dbErrorString = "";
        }
        public HttpResponse(Boolean dbConfirmation,string dbErrorString)
        {
            this.dbConfirmation = dbConfirmation;
            this.dbErrorString = dbErrorString;
        }
    }
}