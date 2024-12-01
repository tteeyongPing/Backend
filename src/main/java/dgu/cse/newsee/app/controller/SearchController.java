package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.app.dto.PlaylistDto;
import dgu.cse.newsee.service.search.SearchServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@Tag(name = "검색 API", description = "검색 관련 API")
@RequiredArgsConstructor
public class SearchController {

    private final SearchServiceImpl searchService;

    // 뉴스 검색
    @GetMapping("/news")
    public ApiResponse<List<NewsDto.NewsRequestDto>> searchNews(@RequestParam("input") String input) {
        List<NewsDto.NewsRequestDto> result = searchService.searchNews(input);
        return ApiResponse.onSuccess(Status.NEWS_SEARCH_SUCCESS, result);
    }

    // 플레이리스트 검색
    @GetMapping("/playlist")
    public ApiResponse<List<PlaylistDto.getPlaylistResponseDto>> searchPlaylist(@RequestParam("input") String input) {
        List<PlaylistDto.getPlaylistResponseDto> result = searchService.searchPlaylist(input);
        return ApiResponse.onSuccess(Status.PLAYLIST_SEARCH_SUCCESS, result);
    }
}
