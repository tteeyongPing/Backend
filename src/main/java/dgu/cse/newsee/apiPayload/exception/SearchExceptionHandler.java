package dgu.cse.newsee.apiPayload.exception;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SearchExceptionHandler {

    // 뉴스 검색 실패
    @ExceptionHandler(SearchException.NewsNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNewsNotFoundException(SearchException.NewsNotFoundException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.NEWS_SEARCH_FAILURE), HttpStatus.NOT_FOUND);
    }

    // 플레이리스트 검색 실패
    @ExceptionHandler(SearchException.PlaylistNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handlePlaylistNotFoundException(SearchException.PlaylistNotFoundException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.PLAYLIST_SEARCH_FAILURE), HttpStatus.NOT_FOUND);
    }
}
