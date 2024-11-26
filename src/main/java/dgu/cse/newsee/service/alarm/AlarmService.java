package dgu.cse.newsee.service.alarm;

import dgu.cse.newsee.app.dto.AlarmDto;
import dgu.cse.newsee.app.dto.AlarmDto.AlarmRequestDto;

import java.util.List;

public interface AlarmService {
    List<AlarmRequestDto> getAlarms(String token);
    void createAlarm(String token, AlarmRequestDto alarmDto);
    void editAlarm(String token, AlarmRequestDto alarmDto);
    void removeAlarm(String token, Long alarmId);
}
