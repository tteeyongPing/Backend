package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PlaylistDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updateNewsRequestDto {
        private Long playlistId;
        private List<Long> newsIdList;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class createRequestDto{
        private String playlistName;
        private String description;
        private List<Long> newsIdList;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class updatePlaylistRequestDto{
        private Long playlistId;
        private String playlistName;
        private String description;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getPlaylistResponseDto {
        private Long playlistId;
        private String playlistName;
        private String description;
        private Long userId;
        private String userName;
        private int subscribers;
        private List<NewsDto> newsList;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewsDto {
        private Long id;
        private String title;
        private String date;
        private String company;
        private String category;
        private String content;
    }
}
