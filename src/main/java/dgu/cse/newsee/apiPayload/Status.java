package dgu.cse.newsee.apiPayload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public enum Status {

    //예시
    TEMP_SUCCESS("200", "SUCCESS", "임시 API 접근에 성공했습니다."),

    // TOKEN 관련
    TOKEN_INVALID("401", "FAILURE", "유효하지 않는 토큰입니다."),
    TOKEN_EXPIRED("401", "FAILURE", "만료된 토큰입니다."),

    // USER 관련
    USER_NON_PRESENT("404", "FAILURE", "존재하지 않는 유저입니다."),
    KAKAO_CODE_SUCCESS("200", "SUCCESS", "카카오 인가코드를 발급받았습니다."),
    KAKAO_LOGIN_SUCCESS("200", "SUCCESS", "카카오 로그인을 성공했습니다."),
    KAKAO_LOGOUT_SUCCESS("200", "SUCCESS", "카카오 로그아웃을 성공했습니다."),
    NICKNAME_SUCCESS("200", "SUCCESS", "닉네임을 변경했습니다."),
    NICKNAME_DUPLICATE("409", "FAILURE", "이미 존재하는 닉네임입니다."),
    LEAVE_SUCCESS("200", "SUCCESS", "회원 탈퇴를 완료했습니다."),

    // NEWS 관련
    NEWS_NON_EXISTS("404", "FAILURE", "존재하지 않는 뉴스입니다."),
    CATEGORY_NON_EXISTS("404", "FAILURE", "존재하지 않는 카테고리 ID 입니다."),

    READ_NEWS_SUCCESS("200", "SUCCESS", "전체 뉴스를 읽었습니다."),
    READ_CATEGORY_NEWS_SUCCESS("200", "SUCCESS", "해당 카테고리의 뉴스를 읽었습니다."),
    READ_NEWS_ALL_SUCCESS("200", "SUCCESS", "전체 뉴스를 읽었습니다."),
    READ_NEWS_SHORTS_SUCCESS("200", "SUCCESS", "해당 뉴스의 요약본입니다."),

    // BOOKMARK 관련
    BOOKMARK_LIST_SUCCESS("200", "SUCCESS", "북마크 목록을 가져왔습니다."),
    BOOKMARK_NEWS_NON_EXISTS("404", "FAILURE", "북마크에 저장된 뉴스가 없습니다."),
    BOOKMARK_ADD_SUCCESS("200", "SUCCESS", "해당 뉴스를 북마크에 추가했습니다."),
    BOOKMARK_DELETE_SUCCESS("200", "SUCCESS", "해당 뉴스를 북마크에서 삭제했습니다."),

    // MEMO 관련
    MEMO_UPDATE_SUCCESS("200", "SUCCESS", "메모를 업데이트했습니다."),
    MEMO_DELETE_SUCCESS("200", "SUCCESS", "메모를 삭제했습니다."),
    MEMO_NOT_FOUND("404", "FAILURE", "메모가 존재하지 않습니다."),

    // PLAYLIST 관련
    MY_PLAYLISTS_SUCCESS("200", "SUCCESS", "내 플레이리스트를 가지고 왔습니다."),
    ADD_TO_PLAYLIST_SUCCESS("200", "SUCCESS", "해당 플레이리스트에 뉴스를 추가했습니다."),
    DELETE_FROM_PLAYLIST_SUCCESS("200", "SUCCESS", "해당 플레이리스트의 뉴스를 삭제했습니다."),
    CREATE_PLAYLIST_SUCCESS("200", "SUCCESS", "플레이리스트를 생성했습니다."),
    EDIT_PLAYLIST_SUCCESS("200", "SUCCESS", "플레이리스트의 정보를 수정했습니다."),
    DELETE_PLAYLIST_SUCCESS("200", "SUCCESS", "해당 플레이리스트를 삭제했습니다."),
    MY_SUBSCRIBE_SUCCESS("200", "SUCCESS", "구독중인 플레이리스트를 가지고 왔습니다."),
    SUBSCRIBE_SUCCESS("200", "SUCCESS", "해당 플레이리스트를 구독했습니다."),
    PLAYLIST_NON_EXISTS("404", "FAILURE", "내 플레이리스트를 찾을 수 없습니다."),
    UNAUTHORIZED_PLAYLIST("403", "FAILURE", "사용자에게 속하지 않은 플레이리스트입니다."),
    NEW_NOT_FOUND_PLAYLIST("404", "FAILURE", "플레이리스트에 해당 뉴스가 존재하지 않습니다."),
    SUBSCRIBE_PLAYLIST_NON_EXISTS("404", "FAILURE", "구독중인 플레이리스트가 없습니다."),
    SUBSCRIBE_MY_PLAYLIST("403", "FAILURE", "내 플레이리스트를 구독할 수 없습니다."),
    ALREADY_SUBSCRIBED("409", "FAILURE", "이미 구독중인 플레이리스트입니다."),

    //Category 관련
    CATEGORY_LIST_SUCCESS("200", "SUCCESS", "관심분야를 가지고 왔습니다."),
    CATEGORY_MY_SUCCESS("200", "SUCCESS", "내 관심분야를 가지고 왔습니다."),
    CATEGORY_EDIT_SUCCESS("200", "SUCCESS", "내 관심분야가 수정되었습니다."),
    CATEGORY_NOT_FOUND("404", "FAILURE", "카테고리가 존재하지 않습니다."),

    //alarm 관련
    ALARM_GET_SUCCESS("200", "SUCCESS", "내 알림을 가지고 왔습니다."),
    ALARM_CREATE_SUCCESS("200", "SUCCESS", "새로운 알림을 설정했습니다."),
    ALARM_EDIT_SUCCESS("200", "SUCCESS", "새로운 알림을 설정했습니다."),
    ALARM_REMOVE_SUCCESS("200", "SUCCESS", "해당 알림을 삭제했습니다."),
    ALARM_NON_EXISTS("404", "FAILURE", "존재하지 않는 알림입니다."),
    ALARM_CREATION_FAILED("500", "FAILURE", "알림 생성에 실패했습니다."),
    ALARM_UPDATE_UNAUTHORIZED("403", "FAILURE", "알림을 업데이트할 권한이 없습니다.");
  
    private final String code;
    private final String result;
    private final String message;
}
