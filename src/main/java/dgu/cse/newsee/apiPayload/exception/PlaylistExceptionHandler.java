package dgu.cse.newsee.apiPayload.exception;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlaylistExceptionHandler {

    @ExceptionHandler(PlaylistException.PlaylistNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handlePlaylistNonExistsException(PlaylistException.PlaylistNonExistsException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.PLAYLIST_NON_EXISTS), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlaylistException.UnauthorizedPlaylistException.class)
    public ResponseEntity<ApiResponse<?>> handleUnauthorizedPlaylistException(PlaylistException.UnauthorizedPlaylistException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.UNAUTHORIZED_PLAYLIST), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(PlaylistException.NewsNotFoundInPlaylistException.class)
    public ResponseEntity<ApiResponse<?>> handleNewsNotFoundInPlaylistException(PlaylistException.NewsNotFoundInPlaylistException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.NEW_NOT_FOUND_PLAYLIST, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlaylistException.SubscribePlaylistNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleSubscribePlaylistNonExistsException(PlaylistException.SubscribePlaylistNonExistsException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.SUBSCRIBE_PLAYLIST_NON_EXISTS), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlaylistException.SubscribeMyPlaylistException.class)
    public ResponseEntity<ApiResponse<?>> handleSubscribeMyPlaylistException(PlaylistException.SubscribeMyPlaylistException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.SUBSCRIBE_MY_PLAYLIST), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlaylistException.AlreadySubscribedException.class)
    public ResponseEntity<ApiResponse<?>> handleAlreadySubscribedException(PlaylistException.AlreadySubscribedException ex){
        return new ResponseEntity<>(ApiResponse.onFailure(Status.ALREADY_SUBSCRIBED), HttpStatus.NOT_FOUND);
    }
}
