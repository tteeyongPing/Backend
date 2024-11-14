package dgu.cse.newsee.service.alarm;

import dgu.cse.newsee.app.dto.AlarmDto;

import java.util.List;

public interface AlarmService {
    public List<AlarmDto> getAlarms(String token);
    public void createAlarm(String token, AlarmDto alarmDto);
    public void editAlarm(String token, AlarmDto alarmDto);
    public void removeAlarm(String token, Long alarmId);
}
