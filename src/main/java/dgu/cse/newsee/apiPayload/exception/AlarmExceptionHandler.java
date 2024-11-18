package dgu.cse.newsee.apiPayload.exception;


import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlarmExceptionHandler {

    // 알림이 존재하지 않을 때 예외 처리
    @ExceptionHandler(AlarmException.AlarmNonExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleAlarmNonExistsException(AlarmException.AlarmNonExistsException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.ALARM_NON_EXISTS), HttpStatus.NOT_FOUND);
    }

    // 알림 생성 실패 시 예외 처리
    @ExceptionHandler(AlarmException.AlarmCreationFailedException.class)
    public ResponseEntity<ApiResponse<?>> handleAlarmCreationFailedException(AlarmException.AlarmCreationFailedException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.ALARM_CREATION_FAILED), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 알림 업데이트 권한이 없을 때 예외 처리
    @ExceptionHandler(AlarmException.AlarmUpdateUnauthorizedException.class)
    public ResponseEntity<ApiResponse<?>> handleAlarmUpdateUnauthorizedException(AlarmException.AlarmUpdateUnauthorizedException ex) {
        return new ResponseEntity<>(ApiResponse.onFailure(Status.ALARM_UPDATE_UNAUTHORIZED), HttpStatus.FORBIDDEN);
    }
}
