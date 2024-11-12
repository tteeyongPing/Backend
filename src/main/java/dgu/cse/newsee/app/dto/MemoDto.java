package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemoDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemoRequestDto{
        private Long newsId;
        private String memo;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemoDeleteRequestDto{
        private Long newsId;
    }
}