package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "🧪 테스트", description = "테스트용 API")
@RequestMapping("api/test")
public class TestController {

    @Operation(summary = "[사용안함]")
    @GetMapping("/testing")
    public ApiResponse<?> test(){
        return ApiResponse.onSuccess(Status.TEMP_SUCCESS, "API 테스트 성공");
    }
}
