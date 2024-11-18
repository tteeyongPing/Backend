package dgu.cse.newsee.apiPayload.exception;

public class CategoryException extends RuntimeException{
    public CategoryException(String message) {
        super(message);
    }

    public static class CategoryNonExistsException extends BookmarkException {
        public CategoryNonExistsException(String message) { super(message); }
    }
}
