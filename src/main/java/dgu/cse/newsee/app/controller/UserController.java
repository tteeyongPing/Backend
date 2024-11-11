package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "🧒🏻 유저", description = "유저 관련 API")
@RequestMapping("api/user")
public class UserController {

    private final UserAccountService userAccountService;

    @Operation(summary = "닉네임 변경")
    @PatchMapping("/nickname/edit")
    public ApiResponse<?> changeNickname(@RequestHeader("Authorization") String token, @RequestParam(value = "nickname") String nickname){
        Long userId = userAccountService.getUserIdFromToken(token);
        userAccountService.updateNickname(userId, nickname);
        return ApiResponse.onSuccess(Status.NICKNAME_SUCCESS, null);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ApiResponse<?> kakaoLogout(@RequestHeader("Authorization") String token){
        String actualToken = userAccountService.getActualTokenFromToken(token);
        userAccountService.logout(actualToken);
        return ApiResponse.onSuccess(Status.KAKAO_LOGOUT_SUCCESS, null);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("leave")
    public ApiResponse<?> leave(@RequestHeader("Authorization") String token){
        Long userId = userAccountService.getUserIdFromToken(token);
        userAccountService.deleteUser(userId);
        return ApiResponse.onSuccess(Status.LEAVE_SUCCESS, null);
    }
}
