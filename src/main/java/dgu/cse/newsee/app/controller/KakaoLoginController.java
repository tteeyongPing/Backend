package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.KakaoLoginDto;
import dgu.cse.newsee.domain.entity.User;
import dgu.cse.newsee.jwt.JWTUtil;
import dgu.cse.newsee.service.kakao.KakaoLoginService;
import dgu.cse.newsee.service.user.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/kakao")
public class KakaoLoginController {

    private final KakaoLoginService kakaoLoginService;
    private final UserAccountService userAccountService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "인가코드 발급 API")
    @GetMapping("/callback")
    public ApiResponse<?> callback(@RequestParam("code") String code){
        return ApiResponse.onSuccess(Status.KAKAO_CODE_SUCCESS, code);
    }

    @Operation(summary = "프론트로부터 카카오 인가코드 전달받기")
    @Parameter(name = "code", description = "카카오에서 받은 인카코드, RequestParam")
    @PostMapping("/login")
    public ApiResponse<?> kakaoLoginCode(@RequestParam("code") String code) {
        String token = kakaoLoginService.getKakaoToken(code);
        KakaoLoginDto.KakaoUserInfoDto kakaoUserInfoDto = kakaoLoginService.getKakaoUserInfo(token);
        if(userAccountService.findByEmail(kakaoUserInfoDto.getEmail()).isEmpty()) userAccountService.saveKakaoUser(kakaoUserInfoDto.getEmail());
        User user = userAccountService.findByEmail(kakaoUserInfoDto.getEmail()).get();

        String jwtToken = jwtUtil.createJwt(user.getId(), 3600000L);
        KakaoLoginDto.LoginResponseDto dto = new KakaoLoginDto.LoginResponseDto().builder()
                .userId(user.getId())
                .token(jwtToken)
                .build();

        return ApiResponse.onSuccess(Status.KAKAO_LOGIN_SUCCESS, dto);
    }
}
