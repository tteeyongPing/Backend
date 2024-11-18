package dgu.cse.newsee.apiPayload.exception;

public class AlarmException extends RuntimeException {

    public AlarmException(String message) {
        super(message);
    }

    // 알림이 존재하지 않을 때 예외
    public static class AlarmNonExistsException extends AlarmException {
        public AlarmNonExistsException(String message) {
            super(message);
        }
    }

    // 알림 생성 시 오류가 발생할 때 예외
    public static class AlarmCreationFailedException extends AlarmException {
        public AlarmCreationFailedException(String message) {
            super(message);
        }
    }

    // 알림 업데이트 시 권한이 없을 때 예외
    public static class AlarmUpdateUnauthorizedException extends AlarmException {
        public AlarmUpdateUnauthorizedException(String message) {
            super(message);
        }
    }
}
