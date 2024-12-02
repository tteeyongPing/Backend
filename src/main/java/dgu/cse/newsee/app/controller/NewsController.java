package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.service.bookmark.BookmarkService;
import dgu.cse.newsee.service.news.NewsService;
import dgu.cse.newsee.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "📰 뉴스", description = "뉴스 관련 API")
@RequestMapping("api/news")
public class NewsController {

    private final NewsService newsService;
    private final UserAccountService userAccountService;
    private final BookmarkService bookmarkService;

    @Operation(summary = "전체 뉴스 가지고오기")
    @GetMapping("/list/all")
    public ApiResponse<?> getNewsListAll(){
        List<News> newsList = newsService.getNewsListAll();
        return ApiResponse.onSuccess(Status.READ_NEWS_SUCCESS, newsList);
    }

    @Operation(summary = "카테고리 별로 뉴스 가지고오기")
    @GetMapping("/list")
    public ApiResponse<?> getNewsList(@RequestParam(value = "category") int categoryId){
        List<News> newsList = newsService.getNewsList(categoryId);
        return ApiResponse.onSuccess(Status.READ_CATEGORY_NEWS_SUCCESS, newsList);
    }

    @Operation(summary = "뉴스의 요약본 가지고오기")
    @GetMapping("/shorts")
    public ApiResponse<?> getNewsShorts(@RequestParam(value = "newsId") Long newsId, @RequestHeader(value = "Authorization", required = false) String token){

        boolean isSubscribe = false;

        if(token != null) {
            Long userId = userAccountService.getUserIdFromToken(token);
            isSubscribe = bookmarkService.checkBookmark(userId, newsId);
        }

        News news = newsService.getNewsShorts(newsId);
        NewsDto.NewsResponseDto dto = NewsDto.NewsResponseDto.builder()
                .shorts(news.getShorts())
                .title(news.getTitle())
                .date(news.getDate())
                .reporter(news.getReporter())
                .category(news.getCategory())
                .company(news.getCompany())
                .content(news.getContent())
                .isSubscribe(isSubscribe)
                .build();
        return ApiResponse.onSuccess(Status.READ_NEWS_SHORTS_SUCCESS, dto);
    }

    @Operation(summary = "뉴스 본문 가져오기")
    @GetMapping("/contents")
    public ApiResponse<?> getNewsContents(@RequestParam(value = "newsId") Long newsId, @RequestHeader(value = "Authorization", required = false) String token){

        boolean isSubscribe = false;

        if(token != null) {
            Long userId = userAccountService.getUserIdFromToken(token);
            isSubscribe = bookmarkService.checkBookmark(userId, newsId);
        }
        News news = newsService.getNews(newsId);
        NewsDto.NewsResponseDto dto = NewsDto.NewsResponseDto.builder()
                .shorts(news.getShorts())
                .title(news.getTitle())
                .date(news.getDate())
                .reporter(news.getReporter())
                .category(news.getCategory())
                .company(news.getCompany())
                .content(news.getContent())
                .isSubscribe(isSubscribe)
                .build();
        return ApiResponse.onSuccess(Status.READ_NEWS_CONTENTS_SUCCESS, dto);
    }
}
