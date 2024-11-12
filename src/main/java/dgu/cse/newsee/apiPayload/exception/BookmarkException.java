package dgu.cse.newsee.apiPayload.exception;

public class BookmarkException extends RuntimeException {

    public BookmarkException(String message) { super(message); }

    public static class BookmarkNonExistsException extends BookmarkException {
        public BookmarkNonExistsException(String message) { super(message); }
    }
}
