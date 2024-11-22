package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestController {

    public ApiResponse<?> test(){
        return ApiResponse.onSuccess(Status.TEMP_SUCCESS, "API 테스트 성공");
    }
}
