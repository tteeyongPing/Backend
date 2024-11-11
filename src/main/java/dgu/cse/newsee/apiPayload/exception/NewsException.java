package dgu.cse.newsee.apiPayload.exception;

public class NewsException extends RuntimeException {

    public NewsException(String message) { super(message); }

    public static class NewsNonExistsException extends NewsException {
        public NewsNonExistsException(String message) { super(message); }
    }

    public static class CategoryNonExistsException extends NewsException{
        public CategoryNonExistsException(String message) { super(message); }
    }
}
