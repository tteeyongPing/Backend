package dgu.cse.newsee.apiPayload.exception;

public class SearchException extends RuntimeException {

    public SearchException(String message) {
        super(message);
    }

    public static class NewsNotFoundException extends SearchException {
        public NewsNotFoundException(String message) {
            super(message);
        }
    }

    public static class PlaylistNotFoundException extends SearchException {
        public PlaylistNotFoundException(String message) {
            super(message);
        }
    }
}
