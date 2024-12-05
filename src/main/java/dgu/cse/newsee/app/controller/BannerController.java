package dgu.cse.newsee.app.controller;

import dgu.cse.newsee.apiPayload.ApiResponse;
import dgu.cse.newsee.apiPayload.Status;
import dgu.cse.newsee.app.dto.BannerDto;
import dgu.cse.newsee.app.dto.NewsDto;
import dgu.cse.newsee.domain.enums.Category;
import dgu.cse.newsee.service.banner.BannerService;
import dgu.cse.newsee.service.category.CategoryService;
import dgu.cse.newsee.service.news.NewsService;
import dgu.cse.newsee.service.user.UserAccountService;
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

    private final UserAccountService userAccountService;
    private final CategoryService categoryService;
    private final BannerService bannerService;

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
        Long userId = userAccountService.getUserIdFromToken(token);
        List<Category> userCategories = categoryService.getCategoriesByUserId(userId);
        NewsDto.NewsAlarmResponseDto randomNews = bannerService.getRandomAlarmNews(userId, userCategories);
        return ApiResponse.onSuccess(Status.BANNER_ALARM_SUCCESS, randomNews);
    }

}
