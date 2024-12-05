package dgu.cse.newsee.service.banner;

import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.service.bookmark.BookmarkService;
import dgu.cse.newsee.service.news.NewsService;
import dgu.cse.newsee.util.RandomGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BannerServiceImpl implements BannerService{

    private final RandomGenerator randomGenerator;
    private final NewsService newsService;
    private final BookmarkService bookmarkService;

    @Override
    public NewsDto.NewsAlarmResponseDto getRandomAlarmNews(Long userId, List<Category> userCategories) {
        Category randomCategory = randomGenerator.getRandomElement(userCategories);
        List<News> newsList = newsService.getNewsList(randomCategory.getId());
        News randomNews = randomGenerator.getRandomElement(newsList);

        boolean isSubscribe = bookmarkService.checkBookmark(userId, randomNews.getId());

        NewsDto.NewsAlarmResponseDto dto = NewsDto.NewsAlarmResponseDto.builder()
                .id(randomNews.getId())
                .title(randomNews.getTitle())
                .content(randomNews.getContent())
                .category(randomNews.getCategory())
                .company(randomNews.getCompany())
                .date(randomNews.getDate())
                .link(randomNews.getLink())
                .reporter(randomNews.getReporter())
                .shorts(randomNews.getShorts())
                .isSubscribe(isSubscribe)
                .build();

        return dto;
    }
}
