package dgu.cse.newsee.apiPayload.exception;

public class MemoException extends RuntimeException {

    public MemoException(String message) {super(message);}

    public static class MemoNotFoundException extends MemoException{
        public MemoNotFoundException(String message) {super(message);}
    }
}