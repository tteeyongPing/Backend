package dgu.cse.newsee.service.alarm;


import dgu.cse.newsee.app.dto.AlarmDto;
import dgu.cse.newsee.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;

    @Autowired
    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    public List<AlarmDto> getAlarms(String token) {
        // TODO: 토큰을 통해 사용자를 인증하고 알림 리스트를 반환
        return alarmRepository.findAlarmsByUserToken(token);
    }

    public void createAlarm(String token, AlarmDto alarmDto) {
        // TODO: 토큰을 통해 사용자를 인증하고 알림을 생성
        alarmRepository.saveAlarm(token, alarmDto);
    }

    public void editAlarm(String token, AlarmDto alarmDto) {
        // TODO: 토큰을 통해 사용자를 인증하고 알림을 수정
        alarmRepository.updateAlarm(token, alarmDto);
    }

    public void removeAlarm(String token, Long alarmId) {
        // TODO: 토큰을 통해 사용자를 인증하고 알림을 삭제
        alarmRepository.deleteAlarmById(token, alarmId);
    }
}

