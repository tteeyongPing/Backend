package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.BookmarkDto;
import dgu.cse.newsee.domain.entity.News;
import dgu.cse.newsee.service.bookmark.BookmarkService;
import dgu.cse.newsee.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "🏷️ 북마크", description = "북마크 관련 API")
@RequestMapping("api/bookmark")
public class BookmarkController {

    private final UserAccountService userAccountService;
    private final BookmarkService bookmarkService;

    @Operation(summary = "북마크 조회")
    @GetMapping("/list")
    public ApiResponse<?> getBookmarkList(@RequestHeader("Authorization") String token){
        Long userId = userAccountService.getUserIdFromToken(token);
        List<News> newsList = bookmarkService.getBookmarkList(userId);
        return ApiResponse.onSuccess(Status.BOOKMARK_LIST_SUCCESS, newsList);
    }

    @Operation(summary = "북마크에 뉴스 추가")
    @PostMapping("/add")
    public ApiResponse<?> addToBookmark(@RequestHeader("Authorization") String token, @RequestBody List<BookmarkDto.BookmarkRequestDto> dtoList){
        Long userId = userAccountService.getUserIdFromToken(token);
        bookmarkService.addNews(userId, dtoList);
        return ApiResponse.onSuccess(Status.BOOKMARK_ADD_SUCCESS, null);
    }

    @Operation(summary = "북마크에서 뉴스 삭제")
    @DeleteMapping("delete")
    public ApiResponse<?> deleteFromBookmark(@RequestHeader("Authorization") String token, @RequestBody List<BookmarkDto.BookmarkRequestDto> dtoList){
        Long userId = userAccountService.getUserIdFromToken(token);
        bookmarkService.deleteNews(userId, dtoList);
        return ApiResponse.onSuccess(Status.BOOKMARK_DELETE_SUCCESS, null);
    }
}
