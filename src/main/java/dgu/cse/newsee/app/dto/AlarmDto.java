package dgu.cse.newsee.app.dto;

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

        private String period;
        private boolean active;
        private List<String> days;


    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AlarmQueryDto {
        private Long alarmId;
        private String period;
        private boolean active;
        private List<String> days;

        public Long getAlarmId() {
            return alarmId;
        }
    }



}
