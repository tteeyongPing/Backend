package dgu.cse.newsee.app.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AlarmDto {
    private Long alarmId;
    private String period;
    private Long userId;
    private boolean active;


    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
