using Open4Tech.Models;

namespace Open4Tech.Services;

public interface INewsService
{
    public Task<List<NewsModel>> GetAllNews();
    public Task<NewsModel?> GetNews(Guid id);
}
