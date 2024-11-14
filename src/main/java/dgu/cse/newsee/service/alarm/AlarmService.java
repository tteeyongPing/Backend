package dgu.cse.newsee.service.alarm;

import dgu.cse.newsee.app.dto.AlarmDto;

import java.util.List;

public interface AlarmService {
    List<AlarmDto> getAlarms(String token);
    void createAlarm(String token, AlarmDto alarmDto);
    void editAlarm(String token, AlarmDto alarmDto);
    void removeAlarm(String token, Long alarmId);
}
