package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
