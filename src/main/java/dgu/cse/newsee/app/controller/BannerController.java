package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.BannerDto;
import dgu.cse.newsee.app.dto.NewsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "🎟️ 배너", description = "배너 관련 API")
@RequestMapping("api/banner")
public class BannerController {

    @Operation(summary = "배너 조회")
    @GetMapping("/list")
    public ApiResponse<?> getBannerList(){
        BannerDto dto1 = BannerDto.builder()
                .imageUrl("https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F05aef0a5-f740-4f24-898f-7a9df606c900%2Fbe36d09f-6b2c-4a5b-9c7f-3f92b211c0df%2Fimage.png?table=block&id=150b4ca7-15b0-8052-8641-d6f6693fb011&spaceId=05aef0a5-f740-4f24-898f-7a9df606c900&width=2000&userId=ecb2a73c-98b9-4dce-8469-3a7fe7256fc5&cache=v2")
                .shorts("네임밸류로는 KBO 역대 최고수준의 야시엘 푸이그가 3년만에 돌아온다.")
                .title("영웅들 28세 캡틴의 강력한 푸이그 신뢰, 이래서 꼭 필요하다")
                .build();

        BannerDto dto2 = BannerDto.builder()
                .imageUrl("https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F05aef0a5-f740-4f24-898f-7a9df606c900%2Fb54c488d-f88a-4d6a-a9e9-4822ba6cd560%2Fimage.png?table=block&id=150b4ca7-15b0-80a1-a350-db2b9f82c6d6&spaceId=05aef0a5-f740-4f24-898f-7a9df606c900&width=2000&userId=ecb2a73c-98b9-4dce-8469-3a7fe7256fc5&cache=v2")
                .shorts("美상무부 대중 반도체 수출통제 발표")
                .title("美, 對中 반도체 수출통제에 삼성전자에 영향")
                .build();

        BannerDto dto3 = BannerDto.builder()
                .imageUrl("https://www.notion.so/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F05aef0a5-f740-4f24-898f-7a9df606c900%2F26226164-9c72-4f79-aecf-357c389bff1c%2Fimage.png?table=block&id=150b4ca7-15b0-80d7-8499-fdceb5539c32&spaceId=05aef0a5-f740-4f24-898f-7a9df606c900&width=2000&userId=ecb2a73c-98b9-4dce-8469-3a7fe7256fc5&cache=v2")
                .shorts(null)
                .title(null)
                .build();

        List<BannerDto> dtoList = new ArrayList<>();
        dtoList.add(dto1);
        dtoList.add(dto2);
        dtoList.add(dto3);
        return ApiResponse.onSuccess(Status.BANNER_LIST_SUCCESS, dtoList);
    }

    @Operation(summary = "알림 뉴스 조회")
    @GetMapping("/alarm/news")
    public ApiResponse<?> getAlarmNews(@RequestHeader("Authorization") String token){

        NewsDto.NewsResponseDto dto = NewsDto.NewsResponseDto.builder()
                .title("산업부 “AI 열풍 필수설비 ‘냉각시스템’…수출 주력 육성”")
                .date("2024-12-02")
                .company("경향신문")
                .shorts("전 세계적으로 AI 기술 수요 증가로 데이터센터와 냉각시스템의 중요성이 커지고 있다.\n" +
                        "정부는 냉각기술 연구·개발에 1300억 원을 지원해 수출 주력 산업으로 육성할 계획이다.\n" +
                        "산업부는 데이터센터 냉각기술을 포함한 냉동공조기계 수출이 2030년까지 2배 성장할 것으로 전망했다.")
                .isSubscribe(false)
                .content("전 세계적으로 인공지능(AI) 기술 관련 수요가 크게 늘며 AI 기술을 구현할 필수재인 AI 데이터센터가 급증하고 있다. 데이터센터를 세울 때 빠질 수 없는 설비는 냉각시스템이다. 정부는 냉각시스템 고도화 등 핵심기술 연구·개발에 1300억원 등을 지원해 수출 주력 산업으로 육성하겠다는 방침을 밝혔다.\n" +
                        "\n" +
                        "산업통상자원부는 안덕근 장관이 2일 경기 평택 LG전자 칠러 공장을 방문해 AI 데이터센터 냉각시스템 생산라인을 둘러보고, 업계 의견을 청취했다고 밝혔다. 칠러는 데이터센터 내부 장비가 과열되지 않도록 파이프를 통해 냉각수를 공급하는 방식을 말한다. 칠러와 함께 데이터센터 냉각시스템으로 주목받는 방식으로는 서버와 같은 장비를 전도성이 없는 특수 액체에 넣는 ‘액침 냉각’도 있다.\n" +
                        "\n" +
                        "데이터센터 냉각시스템을 포함하는 냉동공조기계 수출 실적은 계속 증가하고 있다. 2021년 21억달러에서 매년 2억달러씩 늘어 올해는 26억달러에 이를 것으로 산업부는 보고 있다. 산업부는 2030년에는 현재의 2배 규모인 172억달러에 달할 것으로 추산한다.\n" +
                        "\n" +
                        "이날 간담회에는 이재성 LG전자 부사장, 이태엽 에이스냉동공조 대표, 서상혁 SK엔무브 부사장 등이 참석했다.\n" +
                        "\n" +
                        "안 장관은 “데이터센터는 글로벌 AI 열풍의 핵심 인프라”라며 “정부는 1300억원 규모의 연구·개발을 지원해 경쟁력 강화를 돕겠다”고 말했다.\n" +
                        "\n" +
                        "이어 “실증 및 시운전 등을 포함한 연구·개발에 총 180억원을 지원하고 국내 데이터센터를 대상으로 하는 실증도 적극 추진하겠다”고 밝혔다.\n")
                .reporter("김경학 기자")
                .category("과학기술")
                .build();

        return ApiResponse.onSuccess(Status.BANNER_ALARM_SUCCESS, dto);

    }


}
