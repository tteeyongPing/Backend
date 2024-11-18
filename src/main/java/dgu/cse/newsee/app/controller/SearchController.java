package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.PlaylistDto;
import dgu.cse.newsee.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // 뉴스 검색
    @GetMapping("/news")
    public ApiResponse<List<PlaylistDto.NewsDto>> searchNews(@RequestParam("input") String input) {
        List<PlaylistDto.NewsDto> result = searchService.searchNews(input);
        return ApiResponse.onSuccess(Status.NEWS_SEARCH_SUCCESS, result);
    }

    // 플레이리스트 검색
    @GetMapping("/playlist")
    public ApiResponse<List<PlaylistDto.getPlaylistResponseDto>> searchPlaylist(@RequestParam("input") String input) {
        List<PlaylistDto.getPlaylistResponseDto> result = searchService.searchPlaylist(input);
        return ApiResponse.onSuccess(Status.PLAYLIST_SEARCH_SUCCESS, result);
    }
}