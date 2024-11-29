package dgu.cse.newsee.app.controller;


import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.AlarmDto.AlarmQueryDto;
import dgu.cse.newsee.app.dto.AlarmDto.AlarmRequestDto;
import dgu.cse.newsee.service.alarm.AlarmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "알람 API", description = "알림 관련 API")
@RequestMapping("/api/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @Autowired
    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    // 알림 가져오기
    @GetMapping
    public ApiResponse<?> getAlarms(@RequestHeader("Authorization") String token) {
        List<AlarmQueryDto> alarms = alarmService.getAlarms(token);
        return ApiResponse.onSuccess(Status.ALARM_GET_SUCCESS, alarms);
    }

    // 알림 주기 생성
    @PostMapping("/create")
    public ApiResponse<Object> createAlarm(@RequestHeader("Authorization") String token, @RequestBody AlarmRequestDto alarmDto) {
        alarmService.createAlarm(token, alarmDto);
        return ApiResponse.onSuccess(Status.ALARM_CREATE_SUCCESS,null);
    }

    // 알림 주기 수정
    @PatchMapping("/edit")
    public ApiResponse<?> editAlarm(@RequestHeader("Authorization") String token, @RequestBody AlarmQueryDto alarmDto) {
        alarmService.editAlarm(token, alarmDto);
        return ApiResponse.onSuccess(Status.ALARM_EDIT_SUCCESS,null);
    }

    // 알림 주기 삭제
    @DeleteMapping("/remove")
    public ApiResponse<?> removeAlarm(@RequestHeader("Authorization") String token, @RequestParam Long alarmId) {
        alarmService.removeAlarm(token, alarmId);
        return ApiResponse.onSuccess(Status.ALARM_REMOVE_SUCCESS,null);
    }
}
