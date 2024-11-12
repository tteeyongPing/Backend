package dgu.cse.newsee.apiPayload.exception;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookmarkExceptionHandler {

    @ExceptionHandler(BookmarkException.BookmarkNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleBookmarkNonExistsException(BookmarkException.BookmarkNonExistsException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.BOOKMARK_NEWS_NON_EXISTS), HttpStatus.NOT_FOUND);
    }
}
