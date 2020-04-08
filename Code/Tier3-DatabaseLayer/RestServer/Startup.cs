using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using WebApplication.Database.Data;
using WebApplication.Database.Repository;

namespace WebApplication
{
    public class Startup
    {
        public IConfiguration Configuration { get;  }
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }
        // For more information on how to configure your application, visit https://go.microsoft.com/fwlink/?LinkID=398940
        public void ConfigureServices(IServiceCollection services)
        {
//            services.AddDbContext<TodoContext>(options =>
//                options.UseSqlServer(System.Configuration.Configuration.GetConnectionString("DefaultConnection")));

          
            services.AddMvc(option => option.EnableEndpointRouting = false).AddXmlDataContractSerializerFormatters();
  
        }

        // This method gets called by the runtime
        // . Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

//            app.UseRouting(); 
//            app.UseAuthorization();
            app.UseMvc();
            app.Run(async (context) => { await context.Response.WriteAsync("MVC didn't find anything'"); });
        // This method gets called by the runtime. Use this method to add services to the container.


//            app.UseEndpoints(endpoints =>
//            {
//                endpoints.MapGet("/", async context => { await context.Response.WriteAsync("MVC didn't find anything'!"); });
//            });
        }
    }
}