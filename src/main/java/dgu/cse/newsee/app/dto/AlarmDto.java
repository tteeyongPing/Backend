package dgu.cse.newsee.app.dto;

import dgu.cse.newsee.domain.enums.Day;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AlarmDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AlarmRequestDto{
        private Long alarmId;
        private String period;
        private boolean active;
        private List<Day> day;
    }


}
