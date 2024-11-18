package dgu.cse.newsee.service.news;

import dgu.cse.newsee.apiPayload.exception.NewsException;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.repository.NewsQueryRepository;
import dgu.cse.newsee.repository.NewsRepository;
import dgu.cse.newsee.repository.UserCategoryRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dgu.cse.newsee.repository.UserCategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsServiceImpl implements NewsService {

    private final NewsQueryRepository newsQueryRepository;
    private final NewsRepository newsRepository;
    private final UserCategoryRepository userCategoryRepository;

    @Override
    public List<News> getNewsList(String categoryId) {
        List<News> newsList = newsQueryRepository.findNewsListByCategory(categoryId);
        return newsList;
    }

    @Override
    public String getNewsShorts(Long newsId) {
        News news = getNewsById(newsId);
        String shorts = news.getShorts();
        if(shorts == null){

        }
        return shorts;
    }

    @Override
    public List<News> getNewsListAll() {
        List<News> newsList = newsQueryRepository.findNewsListAll();
        return newsList;
    }
    @Override
    public News getNewsById(Long newsId) {
        Optional<News> news = newsRepository.findById(newsId);
        if(news.isEmpty()) {throw new NewsException.NewsNonExistsException("존재하지 않는 뉴스입니다.");}
        return news.get();
    }
}
