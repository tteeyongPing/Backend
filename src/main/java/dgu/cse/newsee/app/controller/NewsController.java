package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.service.news.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "📰 뉴스", description = "뉴스 관련 API")
@RequestMapping("api/news")
public class NewsController {

    private final NewsService newsService;

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
        return ApiResponse.onSuccess(Status.READ_NEWS_SUCCESS, newsList);
    }

    @Operation(summary = "뉴스의 요약본 가지고오기")
    @GetMapping("/shorts")
    public ApiResponse<?> getNewsShorts(@RequestParam(value = "newsId") Long newsId){
        String shorts = newsService.getNewsShorts(newsId);
        return ApiResponse.onSuccess(Status.READ_NEWS_SHORTS_SUCCESS, shorts);
    }
}
