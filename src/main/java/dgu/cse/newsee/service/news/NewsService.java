package dgu.cse.newsee.service.news;

import dgu.cse.newsee.domain.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getNewsList(int categoryId);
    String getNewsShorts(Long newsId);
    List<News> getNewsListAll();
}
