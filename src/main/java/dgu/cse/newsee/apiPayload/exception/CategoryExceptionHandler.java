package dgu.cse.newsee.apiPayload.exception;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CategoryExceptionHandler {
    @ExceptionHandler(CategoryException.CategoryNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> CategoryNonExistsException(CategoryException.CategoryNonExistsException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.CATEGORY_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
