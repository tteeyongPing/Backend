package dgu.cse.newsee.apiPayload.exception;

public class PlaylistException extends RuntimeException {

    public PlaylistException(String message) {super(message);}

    public static class PlaylistNonExistsException extends PlaylistException{
        public PlaylistNonExistsException(String message) {super(message);}
    }

    public static class UnauthorizedPlaylistException extends PlaylistException{
        public UnauthorizedPlaylistException(String message) {super(message);}
    }

    public static class NewsNotFoundInPlaylistException extends PlaylistException{
        public NewsNotFoundInPlaylistException(String message) {super(message);}
    }

    public static class SubscribePlaylistNonExistsException extends PlaylistException{
        public SubscribePlaylistNonExistsException(String message) {super(message);}
    }

    public static class NonSubscribePlaylistException extends PlaylistException{
        public NonSubscribePlaylistException(String message) {super(message);}
    }

    public static class SubscribeMyPlaylistException extends PlaylistException{
        public SubscribeMyPlaylistException(String message) {super(message);}
    }

    public static class AlreadySubscribedException extends PlaylistException{
        public AlreadySubscribedException(String message) {super(message);}
    }
}
