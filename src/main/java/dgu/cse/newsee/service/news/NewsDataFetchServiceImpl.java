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
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsDataFetchServiceImpl implements NewsFetchService{

    private final NewsRepository newsRepository;
    private final NewsRedisService redisService;
    private static final String NEWS_FETCH_KEY = "newsdata:fetched:today";

    private static final String BASE_URL = "https://newsdata.io/api/1/latest";
    private static final List<String> CATEGORIES = Arrays.asList("business", "entertainment", "health", "science", "sports", "technology");

    @Value("${newsdata_api_key}")
    private String NEWSDATA_API_KEY;


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
                    .queryParam("apiKey", NEWSDATA_API_KEY)
                    .queryParam("category", category)
                    .queryParam("country", "kr")
                    .queryParam("language", "ko")
                    .build()
                    .toUri();

            NewsDto.NewsDataApiResponse response = restTemplate.getForObject(uri, NewsDto.NewsDataApiResponse.class);

            if (response == null || !response.getStatus().equals("success")) {
                System.err.println("Failed to fetch news for category: " + category);
                continue;
            }
            if (response.getTotalResults() == 0) {
                System.out.println("No news available for category: " + category);
                continue;
            }

            List<NewsDto.NewsDataApiResponse.Result> articles = response.getResults();
            if (articles == null || articles.isEmpty()) {
                continue;
            }

            articles.forEach(article -> {
                if (article.getTitle() == null || article.getTitle().isEmpty() ||
                        article.getDescription() == null || article.getDescription().isEmpty() ||
                        article.getSource_name() == null || article.getSource_name().isEmpty() ||
                        article.getCreator() == null || article.getCreator().isEmpty() ||
                        article.getCategory() == null || article.getCategory().isEmpty() || article.getCategory().get(0) == null ||
                        article.getPubDate() == null || article.getPubDate().isEmpty()) {
                    return;
                }

                NewsDto.NewsSaveDto dto = NewsDto.NewsSaveDto.builder()
                        .title(article.getTitle())
                        .content(article.getDescription())
                        .company(article.getSource_name())
                        .reporter(article.getCreator().toString())
                        .category(Category.getKoreanByEnglish(article.getCategory().get(0)))
                        .date(DateUtil.extractDateFromCustomFormat(article.getPubDate()))
                        .link(article.getLink())
                        .build();
                saveNews(dto);
            });
            redisService.setKeyWithExpiry(NEWS_FETCH_KEY, "true", DateUtil.getDurationUntilMidnight());
        }
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
