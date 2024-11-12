package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BookmarkDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookmarkRequestDto {
        private Long newsId;
    }
}
