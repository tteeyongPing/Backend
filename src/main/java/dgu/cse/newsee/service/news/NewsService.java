package dgu.cse.newsee.service.news;

import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.enums.Category;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<News> getNewsList(int categoryId);
    String getNewsShorts(Long newsId);
    List<News> getNewsListAll();
    News getNewsById(Long newsId);

}
