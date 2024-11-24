package dgu.cse.newsee.service.alarm;

import dgu.cse.newsee.apiPayload.exception.AlarmException;
import dgu.cse.newsee.app.dto.AlarmDto;
import dgu.cse.newsee.domain.entity.Alarm;
import dgu.cse.newsee.repository.AlarmRepository;
import dgu.cse.newsee.service.user.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmServiceImpl implements AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserAccountService userAccountService;

    @Override
    public List<AlarmDto> getAlarms(String token) {
        Long userId = userAccountService.getUserIdFromToken(token);
        List<Alarm> alarms = alarmRepository.findByUserId(userId);
        if (alarms.isEmpty()) {
            throw new AlarmException.AlarmNonExistsException("등록된 알림이 없습니다.");
        }
        return alarms.stream()
                .map(alarm -> new AlarmDto(alarm.getId(), alarm.getPeriod(), alarm.getUserId(), alarm.isActive(),alarm.getDay()))
                .collect(Collectors.toList());
    }

    @Override
    public void createAlarm(String token, AlarmDto alarmDto) {
        Long userId = userAccountService.getUserIdFromToken(token);
        Alarm alarm = new Alarm(userId, alarmDto.getPeriod(), alarmDto.isActive(), alarmDto.getDay());
        alarmRepository.save(alarm);
    }

    @Override
    public void editAlarm(String token, AlarmDto alarmDto) {
        Long userId = userAccountService.getUserIdFromToken(token);
        Alarm alarm = alarmRepository.findById(alarmDto.getAlarmId())
                .orElseThrow(() -> new AlarmException.AlarmNonExistsException("해당 알림이 없습니다."));
        if (!alarm.getUserId().equals(userId)) {
            throw new SecurityException("수정 권한이 없습니다.");
        }
        alarm.setPeriod(alarmDto.getPeriod());
        alarm.setActive(alarmDto.isActive());
        alarmRepository.save(alarm);
    }

    @Override
    public void removeAlarm(String token, Long alarmId) {
        Long userId = userAccountService.getUserIdFromToken(token);
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new AlarmException.AlarmNonExistsException("해당 알림이 없습니다."));
        if (!alarm.getUserId().equals(userId)) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }
        alarmRepository.delete(alarm);
    }
}
