package dgu.cse.newsee.service.news;

import dgu.cse.newsee.app.dto.NewsDto;

public interface NewsFetchService {
    public boolean hasFetchedToday();
    public void fetchNews();
    public void saveNews(NewsDto.NewsSaveDto dto);
}
