package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class NewsDto {

    private Long newsId;
    private String category;
    private String title;
    private String date;
    private String content;
    private String company;
    private String shorts;
    private String reporter;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class NewsSaveDto{
        private String category;
        private String title;
        private String date;
        private String content;
        private String company;
        private String shorts;
        private String reporter;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewsApiResponseDto{
        private String status;
        private int totalResults;
        private List<Article> articles;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Article{
            private Source source;
            private String author;
            private String title;
            private String description;
            private String urlToImage;
            private String publishedAt;
            private String content;

            @Getter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Source {
                private String id;
                private String name;
            }
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewsDataApiResponse {
        private String status;
        private int totalResults;
        private List<Result> results;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Result {
            String article_id;
            String title;
            String link;
            Object keywords;
            List<String> creator;
            String video_url;
            String description;
            String content;
            String pubDate;
            String pubDateTZ;
            String image_url;
            String source_id;
            int source_priority;
            String source_name;
            String source_icon;
            String language;
            List<String> country;
            List<String> category;
            Object ai_tag;
            Object ai_region;
            String ai_org;
            String sentiment;
            List<SentimentStatus> sentiment_status;
            boolean duplicate;

            @Getter
            @AllArgsConstructor
            @NoArgsConstructor
            public static class SentimentStatus {
                double positive;
                double neutral;
                double negative;
            }

        }
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewsRequestDto{
        private Long newsId;
        private String title;
        private String date;
        private String shorts;
        private String reporter;
        private String company;

    }
}
