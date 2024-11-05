package dgu.cse.newsee.apiPayload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Status {

    //예시
    TEMP_SUCCESS("200", "SUCCESS", "임시 API 접근에 성공했습니다.");
    private final String code;
    private final String result;
    private final String message;
}
