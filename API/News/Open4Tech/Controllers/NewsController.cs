using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.ML;
using Microsoft.ML.Data;
using Open4Tech.Models;
using Open4Tech.Services;

namespace Open4Tech.Controllers;

[Route("api/[controller]/[action]")]
[ApiController]
public class NewsController : ControllerBase
{
    protected INewsService NewsService { get; set; }
    public NewsController(INewsService newsService)
    {
        NewsService = newsService;
    }

    [HttpGet]
    public async Task<IActionResult> GetAllNews()
    {
        var news = await NewsService.GetAllNews();
        return Ok(news);
    }

    [HttpGet]
    public async Task<IActionResult> GetNewsById(Guid id)
    {
        var news = await NewsService.GetNews(id);
        
        return (news is not null) ? Ok(news) : NotFound("News not found");
    }
}
