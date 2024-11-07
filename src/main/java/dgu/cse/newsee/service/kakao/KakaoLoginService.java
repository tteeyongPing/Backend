package dgu.cse.newsee.service.kakao;

import dgu.cse.newsee.app.dto.KakaoLoginDto;

public interface KakaoLoginService {

    String getKakaoToken(String code);
    KakaoLoginDto.KakaoUserInfoDto getKakaoUserInfo(String token);

}
