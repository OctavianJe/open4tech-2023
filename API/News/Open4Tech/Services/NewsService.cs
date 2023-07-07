using Open4Tech.Models;

namespace Open4Tech.Services;

public class NewsService : INewsService
{
    public NewsService() { }

    List<NewsModel> NewsList { get; } = new List<NewsModel>
    {
        new NewsModel{
            Id = new Guid("0438d632-44a7-46e1-a1c9-a66aadbf1010"), 
            Author="Matthew Cox", 
            Description="Mark Zuckerberg appears to have accepted Elon Musk's challenge to a cage fight after the pair's most recent online spat. Find out all the  stages of this petty feud since 2016 here.", 
            PublishedAt = new DateTime(2022, 12, 12), 
            Title="Million Dollar Babies: How long-running childish beef between Mark Zuckerberg and Elon Musk evolved"
        },
        new NewsModel{
            Id = new Guid("918a8224-9fd4-42a2-84dd-08256ee0f448"), 
            Author="John Boitnott", 
            Description="Just like General Motors and Ford, electric vehicle (EV) manufacturer Rivian has announced that it would be joining Tesla’s charging network. This significant change highlights the expanding role of Tesla’s technology in establishing benchmarks in a variety o…", 
            PublishedAt = new DateTime(2022, 12, 12), Title="Rivian Embraces Tesla’s Charging Network, Pushing The Industry Towards Standardization"
        },
        new NewsModel{
            Id = new Guid("0997fafc-8e08-4694-842a-c4c0271e8939"), 
            Author="USA TODAY, Natalie Neysa Alund, USA TODAY", 
            Description="\"The ex-pro kickboxer, currently on house arrest, volunteered on the heels of being banned from Meta's platforms.", 
            PublishedAt = new DateTime(2023, 12, 12), 
            Title="Andrew Tate says he wants to train Elon Musk to fight Zuckerberg: 'You will not lose"
        },
        new NewsModel{
            Id = new Guid("b07609fe-40f0-4352-8eb8-98c0c70d663c"), 
            Author="barrons.com\"", 
            Description="SpaceX is making the hard look routine. Another 47 Starlink satellites just went into orbit. On Thursday, a Falcon 9 rocket launched the satellites to low-Earth orbit from the space launch complex at Vandenberg Space Force Base in California. Launches have be…", 
            PublishedAt = new DateTime(2023, 12, 12), 
            Title="SpaceX Is Making Space Launches the Norm"
        },
        new NewsModel{
            Id = new Guid("9569fbef-6469-4735-9a00-f252188be3c3"), 
            Author="Megan Sauer", 
            Description="A single Pilates workout inspired Anne Mahlum to spend her entire life savings on a fitness startup, Solidcore. Ten years later, she sold it for millions.", 
            PublishedAt = new DateTime(2023, 12, 12), 
            Title="42-year-old turned $175,000 into a Pilates company—then sold it for $88.4 million: 'I put every dollar I had' into it"
        }
    };

    public async Task<List<NewsModel>> GetAllNews()
    {
        // Simulate response time from Database
        await Task.Delay(1);

        return NewsList;
    }

    public async Task<NewsModel?> GetNews(Guid id)
    {
        await Task.Delay(1);

        var foundNewsItem = NewsList.Find(x => x.Id == id);

        return foundNewsItem != null ? foundNewsItem : null;
    }
}
