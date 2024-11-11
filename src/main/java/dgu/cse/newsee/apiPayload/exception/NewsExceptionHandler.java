package dgu.cse.newsee.apiPayload.exception;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NewsExceptionHandler {

    @ExceptionHandler(NewsException.NewsNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleNewsNonExistsException(NewsException.NewsNonExistsException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.TOKEN_INVALID), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NewsException.CategoryNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleCategoryNonExistsException(NewsException.CategoryNonExistsException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.CATEGORY_NON_EXISTS), HttpStatus.BAD_REQUEST);
    }

}
