namespace Open4Tech.Models;

public class NewsModel
{
    public Guid Id { get; set; }
    public string Author { get; set; }
    public string Title { get; set; }
    public string Description { get; set; } 
    public DateTime PublishedAt { get; set; }
}
