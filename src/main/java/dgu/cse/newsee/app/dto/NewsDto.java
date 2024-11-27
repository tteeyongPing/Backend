package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
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
    public static class NewsRequestDto{
        private Long newsId;
        private String title;
        private String date;
        private String shorts;
        private String reporter;
        private String company;

    }
}
