package dgu.cse.newsee.apiPayload.exception;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemoExceptionHandler {

    @ExceptionHandler(MemoException.MemoNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleMemoNotFoundException(MemoException.MemoNotFoundException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.MEMO_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}