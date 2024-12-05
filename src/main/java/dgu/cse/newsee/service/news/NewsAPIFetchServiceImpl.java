package dgu.cse.newsee.service.news;

import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.repository.NewsRepository;
import dgu.cse.newsee.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsAPIFetchServiceImpl implements NewsFetchService{

    private final NewsRepository newsRepository;
    private final NewsRedisService redisService;
    private static final String NEWS_FETCH_KEY = "news:fetched:today";

    private static final String BASE_URL = "https://newsapi.org/v2/top-headlines";
    private static final List<String> CATEGORIES = Arrays.asList("business", "entertainment", "general", "health", "science", "sports", "technology");
    @Value("${news.news_api_key}")
    private String NEWS_API_KEY;

    @Override
    public boolean hasFetchedToday() {
        return redisService.hasFetchedToday(NEWS_FETCH_KEY);
    }

    @Override
    public void fetchNews() {
        if (hasFetchedToday()) { return; }

        RestTemplate restTemplate = new RestTemplate();

        for (String category : CATEGORIES) {
            URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("apiKey", NEWS_API_KEY)
                    .queryParam("category", category)
                    .queryParam("country", "us")
                    .build()
                    .toUri();

            NewsDto.NewsApiResponseDto response = restTemplate.getForObject(uri, NewsDto.NewsApiResponseDto.class);
            if (response == null || !response.getStatus().equals("ok")) {
                continue;
            }

            if (response.getTotalResults() == 0) {
                continue;
            }

            List<NewsDto.NewsApiResponseDto.Article> articles = response.getArticles();

            if (articles == null || articles.isEmpty()) {
                continue;
            }

            articles.forEach(article -> {
                if (article.getTitle() == null || article.getTitle().isEmpty() ||
                        article.getContent() == null || article.getContent().isEmpty() ||
                        article.getSource() == null || article.getSource().getName() == null || article.getSource().getName().isEmpty() ||
                        article.getAuthor() == null || article.getAuthor().isEmpty() ||
                        category == null || category.isEmpty() ||
                        article.getPublishedAt() == null || article.getPublishedAt().isEmpty()) {
                    return;
                }

                NewsDto.NewsSaveDto dto = NewsDto.NewsSaveDto.builder()
                        .title(article.getTitle())
                        .content(article.getContent())
                        .company(article.getSource().getName())
                        .reporter(article.getAuthor())
                        .category(Category.getKoreanByEnglish(category))
                        .date(DateUtil.extractDate(article.getPublishedAt()))
                        .link(article.getUrl())
                        .build();
                saveNews(dto);
            });
        }
        redisService.setKeyWithExpiry(NEWS_FETCH_KEY, "true", DateUtil.getDurationUntilMidnight());
    }

    @Override
    public void saveNews(NewsDto.NewsSaveDto dto) {
        News news = News.builder()
                .date(dto.getDate())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .company(dto.getCompany())
                .content(dto.getContent())
                .reporter(dto.getReporter())
                .link(dto.getLink())
                .build();
        newsRepository.save(news);
    }
}