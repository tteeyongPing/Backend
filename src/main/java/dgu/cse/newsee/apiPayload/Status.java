package dgu.cse.newsee.apiPayload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Status {

    //예시
    TEMP_SUCCESS("200", "SUCCESS", "임시 API 접근에 성공했습니다."),

    KAKAO_CODE_SUCCESS("200", "SUCCESS", "카카오 인가코드를 발급받았습니다."),
    KAKAO_LOGIN_SUCCESS("200", "SUCCESS", "카카오 로그인을 성공했습니다.");
    private final String code;
    private final String result;
    private final String message;
}
