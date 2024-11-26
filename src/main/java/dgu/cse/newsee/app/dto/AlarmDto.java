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
        private List<String> days; // 변경된 부분


    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AlarmQueryDto {
        private Long alarmId;
        private String period;
        private boolean active;
        private List<String> days; // 변경된 부분

        public Long getAlarmId() {
            return alarmId;
        }
    }



}
